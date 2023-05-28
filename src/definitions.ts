import { PermissionState } from "@capacitor/core";

export interface SignalPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getSignalInfo(): Promise<{ value: any }>;
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(): Promise<PermissionStatus>;
}

export interface PermissionStatus {
  WIFI: PermissionState;
  LTE: PermissionState;
  MICROPHONE: PermissionState;
}
