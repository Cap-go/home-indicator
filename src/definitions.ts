export interface HomeIndicatorPlugin {
  hide(): Promise<void>;
  show(): Promise<void>;
}
