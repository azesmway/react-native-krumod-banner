import { requireNativeComponent, UIManager, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-krumod-banner' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

export interface KrumodBannerProps {}

const ComponentName = 'KrumodBannerView';

export const KrumodBannerView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<KrumodBannerProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
