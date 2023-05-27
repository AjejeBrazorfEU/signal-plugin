import { registerPlugin } from '@capacitor/core';

import type { SignalPluginPlugin } from './definitions';

const SignalPlugin = registerPlugin<SignalPluginPlugin>('SignalPlugin', {
  web: () => import('./web').then(m => new m.SignalPluginWeb()),
});

export * from './definitions';
export { SignalPlugin };
