import { WebPlugin } from '@capacitor/core';

import type { SignalPluginPlugin } from './definitions';

export class SignalPluginWeb extends WebPlugin implements SignalPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
