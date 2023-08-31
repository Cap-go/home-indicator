export interface HomeIndicatorPlugin {
  /**
   * Hide the home indicator.
   *
   * @since 0.0.1
   * @return {Promise<void>}
   */
  hide(): Promise<void>;
  /**
   * Show the home indicator.
   *
   * @since 0.0.1
   * @return {Promise<void>}
   */
  show(): Promise<void>;
  /**
   * Get the home indicator status.
   *
   * @since 0.0.1
   * @return {Promise<{hidden: boolean}>}
   */
  isHidden(): Promise<{ hidden: boolean }>;
  /**
   * Get the native Capacitor plugin version
   *
   * @returns {Promise<{ id: string }>} an Promise with version for this device
   * @throws An error if the something went wrong
   */
  getPluginVersion(): Promise<{ version: string }>;
}
