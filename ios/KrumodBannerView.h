#if __has_include(<React/RCTComponent.h>)
#import <React/RCTView.h>
#else
#import "RCTView.h"
#endif

#import <React/RCTEventDispatcher.h>

@interface KrumodBannerView: RCTView

@property (nonatomic) NSInteger *percentVisibility;

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher;

@end
