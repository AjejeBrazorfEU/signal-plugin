export interface SignalPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
