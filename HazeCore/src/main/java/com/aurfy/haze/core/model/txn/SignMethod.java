package com.aurfy.haze.core.model.txn;

import java.util.HashMap;
import java.util.Map;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;
import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;

@UseEnumTypeHandler("com.aurfy.haze.dao.handler.SignMethodTypeHandler")
public class SignMethod {

	private String name;
	private CipherAlgorithmEnum cipherAlgorithm;

	private static Map<String, SignMethod> nameMap = new HashMap<String, SignMethod>(6);

	public static final SignMethod MD5 = new SignMethod(CipherAlgorithmEnum.MD5);
	public static final SignMethod SHA3 = new SignMethod(CipherAlgorithmEnum.SHA3);
	public static final SignMethod RSA = new SignMethod(CipherAlgorithmEnum.RSA);

	private SignMethod(CipherAlgorithmEnum cipherAlgorithm) {
		super();
		this.name = cipherAlgorithm.name();
		this.cipherAlgorithm = cipherAlgorithm;
		nameMap.put(this.name, this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CipherAlgorithmEnum getCipherAlgorithm() {
		return cipherAlgorithm;
	}

	public void setCipherAlgorithm(CipherAlgorithmEnum cipherAlgorithm) {
		this.cipherAlgorithm = cipherAlgorithm;
	}

	public static SignMethod parseByName(String name) {
		return nameMap.get(name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignMethod [name=");
		builder.append(name);
		builder.append(", cipherAlgorithm=");
		builder.append(cipherAlgorithm);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cipherAlgorithm == null) ? 0 : cipherAlgorithm.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignMethod other = (SignMethod) obj;
		if (cipherAlgorithm != other.cipherAlgorithm)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
