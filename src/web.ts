import { WebPlugin, registerPlugin } from '@capacitor/core';
import { PermissionStatus } from './definitions';

import type { SignalPluginPlugin } from './definitions';
import { PermissionState } from "@capacitor/core";

export class SignalPluginWeb extends WebPlugin implements SignalPluginPlugin {
  checkPermissions(): Promise<PermissionStatus> {
    return new Promise((resolve) => {
      console.log('checkPermissions');
      resolve({
        WIFI: "granted" as PermissionState,
        LTE: "granted" as PermissionState,
        MICROPHONE: "granted" as PermissionState
      });
    });
  }


  requestPermissions(): Promise<PermissionStatus> {
    throw this.unimplemented('Not implemented on web.');
  }
  async getSignalInfo(): Promise<{ value: any; }> {
    console.log('getSignalInfo');
    return { value: {
      "LTE": -20,
      "WIFI": -30,
      "MICROPHONE": 2000
    }}
    
    //throw new Error('Method not implemented.');
  }
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

const SignalPlugin = new SignalPluginWeb();

registerPlugin('SignalPlugin', {
  web: () => Promise.resolve(SignalPlugin),
});

export { SignalPlugin };
