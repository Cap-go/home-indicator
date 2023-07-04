import { WebPlugin } from '@capacitor/core';

import type { HomeIndicatorPlugin } from './definitions';

export class HomeIndicatorWeb extends WebPlugin implements HomeIndicatorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
