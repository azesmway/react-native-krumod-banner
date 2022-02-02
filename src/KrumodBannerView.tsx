import { requireNativeComponent } from 'react-native';
import React from 'react';

const ComponentName = 'KrumodBannerView';

const KrumodBanner = requireNativeComponent(ComponentName);

const BANNER_STATE_TYPE = {
  BANNER_NOT_VISIBLE: 0,
  BANNER_PARTIALLY_VISIBLE: 1,
  BANNER_FULLY_VISIBLE: 2,
};

interface INativeEvent {
  nativeEvent: {
    visible?: number;
  };
}

interface IKrumodBannerView {
  idBanner: number;
  percentVisibility?: number;
  onAdVisibleChange?: (visible: number | undefined) => void;
}

export default class KrumodBannerView extends React.Component<IKrumodBannerView> {
  static defaultProps = {
    idBanner: -1,
    percentVisibility: 50,
    onAdVisibleChange: null,
  };

  constructor(props?: any) {
    super(props);
  }

  /**
   * Banner visibility handler
   * @param event
   */
  onAdVisibleChangeHandler = (event: INativeEvent) => {
    const { onAdVisibleChange } = this.props;
    const visible: number = event.nativeEvent.visible
      ? event.nativeEvent.visible
      : BANNER_STATE_TYPE.BANNER_NOT_VISIBLE;

    onAdVisibleChange && onAdVisibleChange(visible);
  };

  private getProps() {
    return {
      ...this.props,
      onAdVisibleChangeReceived: this.onAdVisibleChangeHandler,
    };
  }

  render() {
    return <KrumodBanner {...this.getProps()} />;
  }
}
