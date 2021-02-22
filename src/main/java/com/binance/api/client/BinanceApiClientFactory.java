package com.binance.api.client;

import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.config.NetType;
import com.binance.api.client.impl.*;

import static com.binance.api.client.config.NetType.*;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.getSharedClient;

/**
 * A factory for creating BinanceApi client objects.
 */
public class BinanceApiClientFactory {


  /**
   * Api Configuration to create the clients
   */
  private final BinanceApiConfig apiConfig;

  /**
   * Instantiates a new binance api client factory.
   *
   * @param apiKey the API key
   * @param secret the Secret
   */
  private BinanceApiClientFactory(String apiKey, String secret) {
    this(apiKey, secret, false);
  }

  /**
   * Instantiates a new binance api client factory.
   *
   * @param apiKey the API key
   * @param secret the Secret
   * @param isTestNet Whether it will connect to the SPOT network or the TEST network
   */
  private BinanceApiClientFactory(String apiKey, String secret, Boolean isTestNet) {
    this.apiConfig = new BinanceApiConfig(apiKey, secret, isTestNet? SPOT_TEST : SPOT);
  }

  /**
   * New instance.
   *
   * @param apiKey the API key
   * @param secret the Secret
   *
   * @return the binance api client factory
   */
  public static BinanceApiClientFactory newInstance(String apiKey, String secret) {
    return new BinanceApiClientFactory(apiKey, secret);
  }

  /**
   * New instance.
   *
   * @param apiKey the API key
   * @param secret the Secret
   * @param isTestNet if it uses the Spot Test Net or the Spot Net
   *
   * @return the binance api client factory
   */
  public static BinanceApiClientFactory newInstance(String apiKey, String secret, Boolean isTestNet) {
    return new BinanceApiClientFactory(apiKey, secret, isTestNet);
  }

  /**
   * New instance without authentication.
   *
   * @return the binance api client factory
   */
  public static BinanceApiClientFactory newInstance() {
    return new BinanceApiClientFactory(null, null);
  }

  /**
   * Creates a new synchronous/blocking REST client.
   */
  public BinanceApiRestClient newRestClient() {
    return new BinanceApiRestClientImpl(apiConfig);
  }

  /**
   * Creates a new asynchronous/non-blocking REST client.
   */
  public BinanceApiAsyncRestClient newAsyncRestClient() {
    return new BinanceApiAsyncRestClientImpl(apiConfig);
  }

  /**
   * Creates a new asynchronous/non-blocking Margin REST client.
   */
  public BinanceApiAsyncMarginRestClient newAsyncMarginRestClient() {
    return new BinanceApiAsyncMarginRestClientImpl(apiConfig);
  }

  /**
   * Creates a new synchronous/blocking Margin REST client.
   */
  public BinanceApiMarginRestClient newMarginRestClient() {
    return new BinanceApiMarginRestClientImpl(apiConfig);
  }

  /**
   * Creates a new web socket client used for handling data streams.
   */
  public BinanceApiWebSocketClient newWebSocketClient() {
    return new BinanceApiWebSocketClientImpl(getSharedClient(), apiConfig);
  }

  /**
   * Creates a new synchronous/blocking Swap REST client.
   */
  public BinanceApiSwapRestClient newSwapRestClient()  {
    return new BinanceApiSwapRestClientImpl(apiConfig);
  }
}
