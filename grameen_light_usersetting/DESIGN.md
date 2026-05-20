---
name: Grameen-Light System
colors:
  surface: '#0f150e'
  surface-dim: '#0f150e'
  surface-bright: '#353b33'
  surface-container-lowest: '#0a1009'
  surface-container-low: '#171d16'
  surface-container: '#1b211a'
  surface-container-high: '#262c24'
  surface-container-highest: '#30362e'
  on-surface: '#dee4d9'
  on-surface-variant: '#becab9'
  inverse-surface: '#dee4d9'
  inverse-on-surface: '#2c322a'
  outline: '#899484'
  outline-variant: '#3f4a3c'
  surface-tint: '#78dc77'
  primary: '#78dc77'
  on-primary: '#00390a'
  primary-container: '#4caf50'
  on-primary-container: '#003c0b'
  inverse-primary: '#006e1c'
  secondary: '#f6be39'
  on-secondary: '#402d00'
  secondary-container: '#c59300'
  on-secondary-container: '#433000'
  tertiary: '#ffb1c7'
  on-tertiary: '#650032'
  tertiary-container: '#f26f9d'
  on-tertiary-container: '#690034'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#94f990'
  primary-fixed-dim: '#78dc77'
  on-primary-fixed: '#002204'
  on-primary-fixed-variant: '#005313'
  secondary-fixed: '#ffdfa0'
  secondary-fixed-dim: '#f6be39'
  on-secondary-fixed: '#261a00'
  on-secondary-fixed-variant: '#5c4300'
  tertiary-fixed: '#ffd9e2'
  tertiary-fixed-dim: '#ffb1c7'
  on-tertiary-fixed: '#3e001c'
  on-tertiary-fixed-variant: '#861948'
  background: '#0f150e'
  on-background: '#dee4d9'
  surface-variant: '#30362e'
typography:
  headline-lg:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: -0.01em
  headline-md:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-md:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.05em
  label-sm:
    fontFamily: Inter
    fontSize: 10px
    fontWeight: '600'
    lineHeight: 14px
    letterSpacing: 0.08em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  gutter: 16px
  margin-mobile: 16px
  margin-desktop: 24px
  stack-sm: 4px
  stack-md: 12px
  stack-lg: 24px
---

## Brand & Style
The design system is engineered for a high-utility financial and agricultural ecosystem, specifically optimized for the Android platform. The brand personality is **grounded, dependable, and growth-oriented**, evoking feelings of stability and accessibility. 

The aesthetic leverages a **Corporate Modern** style with a dark-mode-first approach. It avoids clinical coldness by utilizing a deep green-black foundation, creating a "midnight meadow" atmosphere that is easy on the eyes during extended use. The interface prioritizes clarity and high-contrast information density to ensure usability across various hardware specifications and lighting conditions.

## Colors
The palette is rooted in a deep, organic dark-green spectrum. The **primary green** (#4CAF50) signifies growth and action, used for critical calls-to-action and progress indicators. The **secondary amber** (#D4A017) provides a sophisticated contrast, reserved for highlighting premium features, alerts, or financial statuses.

The background uses a near-black green to maintain deep blacks on OLED screens while retaining the brand’s organic character. Surfaces use a slightly lighter, desaturated green-grey to create a clear visual hierarchy for cards and modal elements.

## Typography
This design system utilizes **Inter** exclusively to ensure maximum legibility and a systematic, technical feel across all Android devices. 

Headlines use a tighter letter-spacing and heavier weights to command attention, while body text maintains standard spacing for readability. Labels are set in a medium weight with slight tracking increases to ensure they remain crisp even at the smallest scale. For mobile-specific contexts, headlines scale down to prevent awkward word wrapping on narrower viewports.

## Layout & Spacing
The layout follows a **fluid grid** model based on an 8px baseline. On mobile devices, a 4-column grid is standard, transitioning to 8 columns for tablets and 12 columns for desktop environments.

Margins are set to 16px on mobile to maximize content real estate while maintaining a clear frame. Gutters are fixed at 16px to ensure enough breathing room between cards and interactive elements. Vertical spacing (stacking) should always be a multiple of the 8px base to maintain a rhythmic vertical flow.

## Elevation & Depth
In this dark mode environment, depth is communicated through **tonal layers** rather than heavy shadows. 

1.  **Level 0 (Base):** The #0D1A0F background.
2.  **Level 1 (Cards/Surfaces):** The #1A2E1A surface color. These should use a subtle 1px stroke (opacity 10% white) to define edges against the dark background.
3.  **Level 2 (Modals/Popups):** A slightly lighter tint of the surface color with a soft, diffused ambient shadow (Black, 40% opacity, 12px blur).

Avoid high-glow effects. Interactions (hovers/presses) should be indicated by a subtle increase in the surface brightness rather than a shadow change.

## Shapes
The design system uses a **rounded** shape language to soften the high-contrast technical aesthetic. This approachable geometry makes the app feel more "human" and less institutional.

The standard corner radius is **12px** (represented as `rounded-md` or `roundedness: 2`). Large containers and cards should use **16px** (`rounded-lg`) to create a clear nesting hierarchy. Small components like tags or checkboxes should scale down to **4px** or **8px** to maintain visual balance.

## Components
- **Buttons:** Primary buttons use the #4CAF50 background with black or high-contrast dark green text. Secondary buttons are outlined in #D4A017 with amber text.
- **Cards:** Use the #1A2E1A surface. They should be flat with the 12px corner radius. Grouped information within cards should be separated by thin, low-opacity dividers.
- **Input Fields:** Backgrounds should be a shade darker than the surface (#122112) with a 1px border that turns #4CAF50 on focus. Labels sit above the field in #9E9E9E.
- **Chips:** Small, highly rounded elements (pill-shaped) used for filtering. Active chips use a subtle amber tint, while inactive chips remain desaturated.
- **Lists:** High-density lists are preferred. Each item should have a minimum touch target of 48dp, with icons tinted in the primary green to guide the eye.
- **Progress Indicators:** Linear or circular bars should use the #4CAF50 primary color against a desaturated track for high visibility.