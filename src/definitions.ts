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
}
