import { WebPlugin } from '@capacitor/core';

import type { HomeIndicatorPlugin } from './definitions';

export class HomeIndicatorWeb extends WebPlugin implements HomeIndicatorPlugin {
  async hide(): Promise<void> {
    this.unimplemented('Not implemented on web.');
    console.log('hide');
  }
  async show(): Promise<void> {
    this.unimplemented('Not implemented on web.');
    console.log('show');
  }
}
