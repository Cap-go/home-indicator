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
}
