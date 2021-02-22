package com.binance.api.client.config;

import static com.binance.api.client.config.NetType.CUSTOM;
import static com.binance.api.client.config.NetType.SPOT;

/**
 * Configuration used for Binance operations.
 */
public class BinanceApiConfig {

	/**
	 * Base domain for URLs.
	 */
	private static final String BASE_DOMAIN = "binance.com";

	/**
	 * Base domain for Test Network URLs
	 */
	private static final String TEST_NET_BASE_DOMAIN = "testnet.binance.vision";

	/**
	 * Base domain
	 */
	private final String baseDomain;

	/**
	 * Network type, ie SPOT or TEST Network
	 */
	private final NetType netType;

	/**
	 * API Key
	 */
	private final String apiKey;

	/**
	 * Secret.
	 */
	private final String secret;

	public BinanceApiConfig() {
		this(null, null, SPOT);
	}

	public BinanceApiConfig(final String apiKey, final String secret, final NetType netType) {
		this.apiKey = apiKey;
		this.secret = secret;
		this.netType = netType;
		switch (netType) {
			case SPOT_TEST:
				this.baseDomain = TEST_NET_BASE_DOMAIN;
				break;
			case SPOT:
			default:
				this.baseDomain = BASE_DOMAIN;
				break;
		}
	}

	public BinanceApiConfig(final String apiKey, final String secret, final String baseDomain) {
		this.apiKey = apiKey;
		this.secret = secret;
		this.baseDomain = baseDomain;
		this.netType = CUSTOM;
	}

	/**
	 * Get the Network Type (e.g. SPOT, SPOT_TEST)
	 * @return The Network Type
	 */
	public NetType getNetType() {
		return  netType;
	}

	/**
	 * Get the Api Key used to authenticate requests to Binance
	 * @return the Api Key
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * Get the Secret used to authenticate requests to Binance
	 * @return the Secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * Get the URL base domain name (e.g., binance.com).
	 *
	 * @return The base domain for URLs
	 */
	public String getBaseDomain() {
		return baseDomain;
	}

	/**
	 * REST API base URL.
	 */
	public String getApiBaseUrl() {
		return netType == SPOT ? String.format("https://api.%s", getBaseDomain()) : String.format("https://%s", getBaseDomain());
	}

	/**
	 * Streaming API base URL.
	 */
	public String getStreamApiBaseUrl() {
		return netType == SPOT ? String.format("wss://stream.%s:9443/ws", getBaseDomain()) : String.format("wss://%s/ws", getBaseDomain());
	}

	/**
	 * Asset info base URL.
	 */
	public String getAssetInfoApiBaseUrl() {
		return String.format("https://%s/", getBaseDomain());
	}

}
