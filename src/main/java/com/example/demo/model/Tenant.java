package com.example.demo.model;


import java.util.Objects;

/**
 *
 * @author ard333
 */
public class Tenant {

	private String code;
	private String name;

	public Tenant() {

	}

	public Tenant(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tenant)) return false;
		Tenant tenant = (Tenant) o;
		return Objects.equals(code, tenant.code) &&
				Objects.equals(name, tenant.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tenant{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
