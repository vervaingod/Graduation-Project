package com.equipment.bean;

public class CostBean {
	private int cost_id;
	private String cost_project;
	private String cost_service;
	private String cost_material;
	private int cost_maintenance_id;

	public int getCost_id() {
		return cost_id;
	}

	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}

	public String getCost_project() {
		return cost_project;
	}

	public void setCost_project(String cost_project) {
		this.cost_project = cost_project;
	}

	public String getCost_material() {
		return cost_material;
	}

	public void setCost_material(String cost_material) {
		this.cost_material = cost_material;
	}

	public int getCost_maintenance_id() {
		return cost_maintenance_id;
	}

	public void setCost_maintenance_id(int cost_maintenance_id) {
		this.cost_maintenance_id = cost_maintenance_id;
	}

	public String getCost_service() {
		return cost_service;
	}

	public void setCost_service(String cost_service) {
		this.cost_service = cost_service;
	}
}
