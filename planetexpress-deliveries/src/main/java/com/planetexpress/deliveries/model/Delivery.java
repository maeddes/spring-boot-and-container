package com.planetexpress.deliveries.model;

import java.util.UUID;

public class Delivery {
	private UUID id;
	private String recipientPlanet;
	private String packageContents;
	private DeliveryStatus status;

	public Delivery(String recipientPlanet, String packageContents) {
		this.id = UUID.randomUUID();
		this.recipientPlanet = recipientPlanet;
		this.packageContents = packageContents;
		this.status = DeliveryStatus.PENDING;
	}

	public UUID getId() {
		return id;
	}

	public String getRecipientPlanet() {
		return recipientPlanet;
	}

	public String getPackageContents() {
		return packageContents;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}
}
