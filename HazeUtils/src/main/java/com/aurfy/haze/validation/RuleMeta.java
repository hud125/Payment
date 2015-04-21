package com.aurfy.haze.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.utils.IOUtils;

public class RuleMeta {

	private static final String TAG_NAME_ALIAS = "alias";
	private static final String TAG_NAME_RULE = "rule";
	private static final String PACKAGE_SEPARATOR = ".";

	private Map<String, String> aliasMap;
	private Map<String, Validator> validatorMap;

	public RuleMeta() {
	}

	public void parse(InputStream in) throws ValidationException {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();

			// alias
			parseAlias(root);

			// rules
			parseRule(root);

		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new ValidationException("error parsing validation rule", e);
		} finally {
			IOUtils.close(in);
		}
	}

	@SuppressWarnings("unchecked")
	private void parseRule(Element parentElement) throws ValidationException {

		Element ruleElement;
		String fieldName, validatorClass;
		Map<String, String> params;
		Validator validator;
		validatorMap = new HashMap<String, Validator>();

		for (Iterator<Element> i = parentElement.elementIterator(TAG_NAME_RULE); i.hasNext();) {
			ruleElement = (Element) i.next();
			fieldName = getRequiredAttribute(ruleElement, TAG_NAME_RULE, "fieldName");
			validatorClass = getRequiredAttribute(ruleElement, TAG_NAME_RULE, "validator");

			// get all the attributes except 'validator'
			params = getAttributeMap(ruleElement);
			params.remove("validator");

			// replace target alias
			if (params.containsKey("target")) {
				params.put("target", replaceAlias(params.get("target")));
			}

			try {
				validator = (Validator) Class.forName(replaceAlias(validatorClass)).newInstance();
				validator.init(params);
				validatorMap.put(fieldName, validator);
			} catch (ValidationException ve) {
				throw ve;
			} catch (Exception e) {
				throw new ValidationException("error parsing validation rule", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void parseAlias(Element parentElement) throws ValidationException {
		aliasMap = new HashMap<String, String>(5);
		Element aliasElement;
		String source, dest;
		Set<String> srcSet = new HashSet<String>(aliasMap.size());
		Set<String> destSet = new HashSet<String>(aliasMap.size());
		try {
			for (Iterator<Element> i = parentElement.elementIterator(TAG_NAME_ALIAS); i.hasNext();) {
				source = null;
				dest = null;
				aliasElement = (Element) i.next();
				source = getRequiredAttribute(aliasElement, TAG_NAME_ALIAS, "source");
				dest = getRequiredAttribute(aliasElement, TAG_NAME_ALIAS, "dest");
				if (srcSet.contains(source)) {
					throw new ValidationException("Alias already defined for source '" + source + "'.");
				} else {
					srcSet.add(source);
				}
				if (destSet.contains(dest)) {
					throw new ValidationException("Alias already defined for dest '" + dest + "'.");
				} else {
					destSet.add(dest);
				}
				aliasMap.put(dest + PACKAGE_SEPARATOR, source + PACKAGE_SEPARATOR);
			}
		} finally {
			// cleanup
			srcSet.clear();
			destSet.clear();
		}
	}

	private static String getRequiredAttribute(Element element, String tag, String attrName) throws ValidationException {
		String attr = element.attributeValue(attrName);
		if (isEmpty(attr)) {
			throw new ValidationException("attribute '" + attrName + "' is required for '" + tag + "' element");
		}
		return attr;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getAttributeMap(Element element) {
		List<Attribute> lists = element.attributes();
		Map<String, String> result = new HashMap<String, String>(CollectionUtils.size(lists));
		if (CollectionUtils.isNotEmpty(lists)) {
			for (Attribute attr : lists) {
				result.put(attr.getName(), attr.getValue());
			}
		}
		return result;
	}

	/**
	 * replace with the first occurrence of alias
	 */
	private String replaceAlias(String simplifiedClass) {
		for (String key : aliasMap.keySet()) {
			if (simplifiedClass.startsWith(key)) {
				return simplifiedClass.replaceFirst(key, aliasMap.get(key));
			}
		}
		return simplifiedClass;
	}

	public Map<String, Validator> getValidatorMap() {
		return validatorMap;
	}
}
