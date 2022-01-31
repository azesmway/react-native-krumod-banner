#import "KrumodBannerView.h"

typedef NS_ENUM(NSInteger)
{
    BANNER_NOT_VISIBLE                 = 0,
    BANNER_PARTIALLY_VISIBLE           = 1,
    BANNER_FULLY_VISIBLE               = 2
};

@implementation KrumodBannerView {
    BOOL _isLoaded;
    NSInteger _isVisible;
}

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher {
    if (self = [super init]) {
        _isLoaded = NO;
        _isVisible = BANNER_NOT_VISIBLE;
    }
    return self;
}

- (void)layoutSubviews {
    super.removeClippedSubviews = YES;
    [super layoutSubviews];
}

- (void)react_updateClippedSubviewsWithClipRect:(CGRect)clipRect relativeToView:(UIView *)clipView
{
    [self setVisibleBanner:clipRect relativeToView:clipView];
}

- (void)setVisibleBanner:(CGRect)clipRect relativeToView:(UIView *)clipView {
    clipRect = [clipView convertRect:clipRect toView:self];
    clipRect = CGRectIntersection(clipRect, [UIScreen mainScreen].bounds);

    if (!CGSizeEqualToSize(CGRectIntersection(clipRect, self.frame).size, CGSizeZero)) {
        if (CGRectContainsRect(clipRect,self.frame)) {
            // View is fully visible, so remount all subviews
            if (_isVisible != BANNER_FULLY_VISIBLE) {
                _isVisible = BANNER_FULLY_VISIBLE;
                NSLog(@"BANNER - BANNER_FULLY_VISIBLE");
                // _onAdVisibleChange(@{@"visible": @(BANNER_FULLY_VISIBLE)});
            }
        } else {
            // View is partially visible, so update clipped subviews
            if (_isVisible != BANNER_PARTIALLY_VISIBLE) {
                _isVisible = BANNER_PARTIALLY_VISIBLE;
                NSLog(@"BANNER - BANNER_PARTIALLY_VISIBLE");
                // _onAdVisibleChange(@{@"visible": @(BANNER_PARTIALLY_VISIBLE)});
            }
        }
    } else {
        if (_isVisible != BANNER_NOT_VISIBLE) {
            _isVisible = BANNER_NOT_VISIBLE;
            NSLog(@"BANNER - BANNER_NOT_VISIBLE");
            // _onAdVisibleChange(@{@"visible": @(BANNER_NOT_VISIBLE)});
        }
    }

    if (_isVisible != BANNER_NOT_VISIBLE) {
        int hV = self.frame.size.height;
        int hR = clipRect.size.height;
        int yR = clipRect.origin.y;

        if (yR <= hV) {
            int percent = 100 - ((hV - hR) * 100 / hV);

            if (clipRect.origin.y > 0) {
                hR = clipRect.origin.y;
                percent = ((hV - hR) * 100 / hV);
            }

            if (hR > 0 && hR <= hV) {
                NSLog(@"BANNER - setVisibleBanner %d", percent);
            }
        }
    }
}

@end
