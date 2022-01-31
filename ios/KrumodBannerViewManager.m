#import <React/RCTViewManager.h>
#import "KrumodBannerView.h"

@interface KrumodBannerViewManager : RCTViewManager
@end

@implementation KrumodBannerViewManager

RCT_EXPORT_MODULE(KrumodBannerView)

- (UIView *)view
{
  return [[KrumodBannerView alloc] initWithEventDispatcher:self.bridge.eventDispatcher];
}

- (instancetype)init
{
    self = [super init];
    return self;
}

RCT_EXPORT_VIEW_PROPERTY(percentVisibility, NSInteger *)

@end
