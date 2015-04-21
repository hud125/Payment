package com.aurfy.haze.utils.rule;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;

import com.aurfy.haze.utils.rule.exceptions.RuleException;

public class RuleGroup implements Rule {

	private static final String GROUP_REPLACEMENT = "{UPOP_RULE_ENGINE_GROUP_REPLACEMENT}";

	private List<Rule> rules;

	private Logic logic;

	public RuleGroup() {
		this(Logic.And);
	}

	public RuleGroup(Logic logic, Rule... rules) {
		this.logic = logic;
		this.rules = new ArrayList<Rule>();
		if (rules != null && rules.length > 0) {
			for (Rule rule : rules) {
				if (!rule.isEmpty()) {
					addRule(rule);
				}
			}
		}
	}

	private static int findMatchingBracket(String str, int startIndex) throws RuleException {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(startIndex);
		for (int i = startIndex + 1; i < str.length(); i++) {
			if ('(' == str.charAt(i)) {
				stack.push(i);
			} else if (')' == str.charAt(i)) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				return i;
			}
		}
		throw new RuleException("No matching right bracket found.");
	}

	public static Rule parse(String str) throws RuleException {
		if (str == null) {
			throw new RuleException("Can not parse null rule.");
		}

		RuleGroup group = new RuleGroup();

		int indexOfFirstBracket = -1, indexOfMatchingBracket = -1;
		String tmp, remain = str;

		// get first left bracket
		indexOfFirstBracket = remain.indexOf('(');

		while (indexOfFirstBracket != -1) {
			indexOfMatchingBracket = findMatchingBracket(remain, indexOfFirstBracket);
			tmp = remain.substring(indexOfFirstBracket + 1, indexOfMatchingBracket);
			group.addRule(parse(tmp));

			tmp = "";
			if (indexOfFirstBracket > 0) {
				tmp += remain.substring(0, indexOfFirstBracket);
			}
			tmp += GROUP_REPLACEMENT;
			if (indexOfMatchingBracket < remain.length() - 1) {
				tmp += remain.substring(indexOfMatchingBracket + 1, remain.length());
			}

			remain = tmp;

			indexOfFirstBracket = remain.indexOf('(');
		}

		// no remaining brackets now
		int indexOfAnd = remain.indexOf(Logic.And.getConnector());
		int indexOfOr = remain.indexOf(Logic.Or.getConnector());

		if (indexOfAnd > -1 && indexOfOr > -1) {
			throw new RuleException("Multiple logic detected.");
		} else if (indexOfAnd > -1) {
			group.setLogic(Logic.And);
		} else if (indexOfOr > -1) {
			group.setLogic(Logic.Or);
		} else if (isBlank(remain)) {
			return group;
		} else if (!GROUP_REPLACEMENT.equals(trim(remain))) {
			SimpleRule sr = SimpleRule.parse(remain);
			if (group.isEmpty()) {
				return sr;
			} else {
				group.addRule(sr);
				return group;
			}
		}
		remain += group.getLogic().getConnector() + GROUP_REPLACEMENT;
		String[] srs = remain.split(Pattern.quote(group.getLogic().getConnector()));
		for (String sr : srs) {
			if (!GROUP_REPLACEMENT.equals(trim(sr))) {
				group.addRule(SimpleRule.parse(sr));
			}
		}
		return group;
	}

	@Override
	public boolean satisfy(Object instance) {
		if (this.isEmpty()) {
			return false;
		}
		if (Logic.And.equals(this.logic)) {
			for (Rule rule : rules) {
				if (!rule.satisfy(instance)) {
					return false;
				}
			}
			return true;
		} else {
			for (Rule rule : rules) {
				if (rule.satisfy(instance)) {
					return true;
				}
			}
			return false;
		}
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	public List<Rule> getRules() {
		return rules;
	}

	public Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}

	@Override
	public boolean isEmpty() {
		if (CollectionUtils.isEmpty(rules)) {
			return true;
		}
		boolean result = true;
		for (Rule rule : rules) {
			if (!rule.isEmpty()) {
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "";
		}
		List<String> inner = new ArrayList<String>(rules.size());
		for (Rule rule : rules) {
			if (!rule.isEmpty()) {
				inner.add(rule.toString());
			}
		}
		StringBuilder builder = new StringBuilder();
		if (inner.size() > 1) {
			builder.append("(");
		}
		builder.append(join(inner, " " + logic.getConnector() + " "));
		if (inner.size() > 1) {
			builder.append(")");
		}
		return builder.toString();
	}
}
