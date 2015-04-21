package com.aurfy.haze.utils.rule;

import static com.aurfy.haze.utils.StringUtils.formatMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aurfy.haze.utils.ReflectionUtils;
import com.aurfy.haze.utils.RegexUtils;
import com.aurfy.haze.utils.ScannerUtils;
import com.aurfy.haze.utils.rule.exceptions.RuleException;

public class SimpleRule implements Rule {

	private static final String REGEX_TPL = "^\\s*(\\w+)\\s*({0})\\s*(''?[\\w,+-\\.]*''?)\\s*$";
	static final Pattern SIMPLE_RULE_PATTERN;
	private static final Map<String, Operator> OPERATOR_MAP;

	static {
		OPERATOR_MAP = new HashMap<String, Operator>();
		Operator op;

		Set<Class<? extends Operator>> opClasses = ScannerUtils.scan4Subclasses(Operator.class, Operator.class
				.getPackage().getName(), false);

		if (opClasses != null) {
			for (Class<? extends Operator> clazz : opClasses) {
				op = ReflectionUtils.newInstance(clazz);
				OPERATOR_MAP.put(op.getLabel(), op);
			}
		}

		SIMPLE_RULE_PATTERN = RegexUtils.getCaseInsensitivePattern(formatMessage(REGEX_TPL,
				join(OPERATOR_MAP.keySet(), '|')));
	}

	private String key;
	private Operator operator;
	private String value;

	public SimpleRule() {
		this(null, null, null);
	}

	public SimpleRule(String key, Operator operator) {
		this(key, operator, null);
	}

	public SimpleRule(String key, Operator operator, String value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
	}

	public static SimpleRule parse(String str) throws RuleException {
		if (str == null) {
			throw new RuleException("Can not parse null rule.");
		}

		SimpleRule rule = new SimpleRule();
		Matcher matcher = SIMPLE_RULE_PATTERN.matcher(str);
		while (matcher.find()) {
			rule.setKey(matcher.group(1));
			String op_label = matcher.group(2);
			if (!OPERATOR_MAP.containsKey(op_label)) {
				throw new RuleException(formatMessage("Operator ''{0}'' is not supported.", op_label));
			}
			rule.setOperator(OPERATOR_MAP.get(op_label));
			String value = matcher.group(3);
			ValueType valueType = rule.getOperator().getValueType();
			if ((ValueType.Text.equals(valueType) || ValueType.Date.equals(valueType))
					&& rule.getOperator().isValueRequired()) {
				value = getStrippedValue(value);
			} else if (!rule.getOperator().isValueRequired() && isNotEmpty(value)) {
				throw new RuleException(formatMessage("Operator ''{0}'' does not need to be followed by a value.", rule
						.getOperator().getLabel()));
			}
			rule.setValue(value);
		}
		if (rule.isEmpty()) {
			throw new RuleException(formatMessage("Expression is not a valid rule: {0}", str));
		}
		return rule;
	}

	private static String getStrippedValue(String value) throws RuleException {
		if (value == null) {
			throw new RuleException("Rule value can not be null.");
		} else if (value.length() < 2) {
			throw new RuleException("Invalid rule value (length < 2).");
		} else if (value.charAt(0) != '\'' || value.charAt(value.length() - 1) != '\'') {
			throw new RuleException("Rule value must be wrapped in single quotation marks.");
		} else {
			return value.substring(1, value.length() - 1);
		}
	}

	@Override
	public boolean satisfy(Object object) {
		try {
			return operator != null && object != null && operator.satisfy(object, key, value);
		} catch (RuleException e) {
			return false;
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean isEmpty() {
		if (isBlank(key)) {
			return true;
		}
		if (operator == null) {
			return true;
		}
		if (operator.isValueRequired() && value == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "";
		}
		StringBuilder buffer = new StringBuilder();
		buffer.append(key);
		buffer.append(' ');
		buffer.append(operator.getLabel());
		if (operator.isValueRequired()) {
			buffer.append(" ");
			if (ValueType.Text.equals(operator.getValueType()) || ValueType.Date.equals(operator.getValueType())) {
				buffer.append("\'");
			}
			buffer.append(value);
			if (ValueType.Text.equals(operator.getValueType()) || ValueType.Date.equals(operator.getValueType())) {
				buffer.append("\'");
			}
		}
		return buffer.toString();
	}
}
