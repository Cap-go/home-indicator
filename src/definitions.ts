export interface HomeIndicatorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
