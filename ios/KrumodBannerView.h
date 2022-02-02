#if __has_include(<React/RCTComponent.h>)
#import <React/RCTView.h>
#else
#import "RCTView.h"
#endif

#import <React/RCTEventDispatcher.h>

@class RCTBridge;

@interface KrumodBannerView: RCTView

@property (nonatomic) NSInteger * _Nullable percentVisibility;
@property (nonatomic) NSString * _Nullable idBanner;

@property (nonatomic, copy) RCTBubblingEventBlock _Nullable onAdVisibleChangeReceived;

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher;

@end
