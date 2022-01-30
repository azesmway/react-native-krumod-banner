#if __has_include(<React/RCTComponent.h>)
#import <React/RCTView.h>
#else
#import "RCTView.h"
#endif

#import <React/RCTEventDispatcher.h>
#import <React/RCTShadowView.h>
#import <React/UIView+React.h>
#import <UIKit/UIKit.h>

@interface KrumodBannerView: RCTView

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher;

@end
