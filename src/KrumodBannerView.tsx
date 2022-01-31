import { requireNativeComponent } from 'react-native';
import React from 'react';

const ComponentName = 'KrumodBannerView';

const KrumodBanner = requireNativeComponent(ComponentName);

interface IKrumodBannerView {
  percentVisibility?: number;
}

export default class KrumodBannerView extends React.Component<IKrumodBannerView> {
  static defaultProps = {
    percentVisibility: 50,
  };

  constructor(props?: any) {
    super(props);
  }

  render() {
    return <KrumodBanner {...this.props} />;
  }
}
