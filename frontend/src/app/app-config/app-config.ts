import { environment } from '../../environments/environment';
import { InjectionToken } from '@angular/core';

export interface AppConfig {
  apiEndpoint: string;
  addressApiEndpoint: string;
  addressApiKey: string;
}

export const APP_SERVICE_CONFIG = new InjectionToken<AppConfig>(
  'app.app-config'
);

export const APP_CONFIG: AppConfig = {
  apiEndpoint: environment.apiEndpoint,
  addressApiEndpoint: environment.addressApiEndpoint,
  addressApiKey: environment.addressApiKey,
};
