---
name: Village Management System
colors:
  surface: '#f9f9f9'
  surface-dim: '#dadada'
  surface-bright: '#f9f9f9'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f3f3f3'
  surface-container: '#eeeeee'
  surface-container-high: '#e8e8e8'
  surface-container-highest: '#e2e2e2'
  on-surface: '#1b1b1b'
  on-surface-variant: '#3c4a42'
  inverse-surface: '#303030'
  inverse-on-surface: '#f1f1f1'
  outline: '#6c7a71'
  outline-variant: '#bbcabf'
  surface-tint: '#006c49'
  primary: '#006c49'
  on-primary: '#ffffff'
  primary-container: '#10b981'
  on-primary-container: '#00422b'
  inverse-primary: '#4edea3'
  secondary: '#586330'
  on-secondary: '#ffffff'
  secondary-container: '#d8e6a6'
  on-secondary-container: '#5c6834'
  tertiary: '#bc0b3b'
  on-tertiary: '#ffffff'
  tertiary-container: '#ff7886'
  on-tertiary-container: '#780021'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#6ffbbe'
  primary-fixed-dim: '#4edea3'
  on-primary-fixed: '#002113'
  on-primary-fixed-variant: '#005236'
  secondary-fixed: '#dbe9a9'
  secondary-fixed-dim: '#bfcd8f'
  on-secondary-fixed: '#171e00'
  on-secondary-fixed-variant: '#404b1b'
  tertiary-fixed: '#ffdadb'
  tertiary-fixed-dim: '#ffb2b7'
  on-tertiary-fixed: '#40000d'
  on-tertiary-fixed-variant: '#92002a'
  background: '#f9f9f9'
  on-background: '#1b1b1b'
  surface-variant: '#e2e2e2'
typography:
  headline-xl:
    fontFamily: Inter
    fontSize: 40px
    fontWeight: '700'
    lineHeight: 48px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  headline-sm:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  label-lg:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 28px
    fontWeight: '700'
    lineHeight: 36px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  xs: 4px
  sm: 12px
  md: 24px
  lg: 40px
  xl: 64px
  container-padding: 24px
  gutter: 16px
---

## Brand & Style

This design system is built for clarity, reliability, and community trust. It facilitates the management of essential village utilities by balancing high-contrast legibility with an approachable, modern aesthetic. 

The style is **Minimalist with High-Contrast Accents**. It utilizes a pure white canvas to emphasize data accuracy, while using bold organic greens to reflect the nature of village life and infrastructure. The interface prioritizes "glanceability"—the ability for users to quickly understand utility status, alerts, and navigation through distinct color-coding and structured card-based layouts.

## Colors

The palette is strictly functional, leveraging high contrast to guide the eye toward critical information.

- **Primary (Vibrant Green):** Used for success states, active navigation indicators, and "good" utility readings.
- **Secondary (Olive Green):** Reserved for infrastructure-related accents and branding elements that require a more grounded, professional tone.
- **Tertiary (Red-Pink):** Specifically for alerts, overdue payments, or critical utility failures.
- **Neutral (Deep Black/Pure White):** All typography is rendered in deep black on pure white backgrounds to maximize AA/AAA accessibility standards.
- **Gold Accent:** Used exclusively for map-based highlights or high-value status indicators.

## Typography

The design system uses **Inter** exclusively to ensure a systematic, neutral, and highly legible experience. 

- **Weighting:** Headlines are heavily weighted (Bold/700) to provide immediate structural hierarchy against the white background.
- **Utility Data:** Numeric readings (meter levels, costs) should use `headline-md` or `headline-lg` to ensure they are the most prominent elements on the page.
- **Microcopy:** Labels and captions use medium weights to maintain readability at small scales.

## Layout & Spacing

This design system follows a **fixed-width card grid** model. On desktop, content is organized into a 12-column grid with generous 24px margins. On mobile, it shifts to a single-column stack.

- **Card-Based Hierarchy:** Information is grouped into white cards. Spacing between cards is typically `md` (24px) to allow the "clean" aesthetic to breathe.
- **Density:** Internal card padding is consistently `md`, creating a comfortable frame for utility data.
- **Reflow:** On smaller screens, horizontal navigation shifts to a bottom bar, while cards expand to fill the width of the viewport minus the `container-padding`.

## Elevation & Depth

Depth is achieved through a combination of **low-contrast outlines** and **ambient shadows**.

- **Surfaces:** All primary containers are pure white (#FFFFFF).
- **Borders:** Cards use a subtle 1px border (#E5E7EB) to define their boundaries against the white background.
- **Shadows:** A single "soft shadow" style is used for interactive cards: `0px 4px 12px rgba(0, 0, 0, 0.05)`. This creates a slight lift without appearing heavy or cluttered.
- **Active State:** Elements that are being interacted with may increase shadow intensity slightly, but never lose the clean border definition.

## Shapes

The shape language is modern and friendly, avoiding the severity of sharp corners while maintaining professional structure.

- **Main Containers:** Use `rounded-lg` (1rem/16px) for all primary data cards.
- **Buttons & Inputs:** Use `rounded` (0.5rem/8px) for a sturdy, reliable feel.
- **Specific Badge Shapes:** Alerts and status badges use the "pill" style (full rounding) to differentiate them from functional containers.

## Components

### Navigation
- **Active State:** Active navigation items in the sidebar or bottom bar are indicated by a **dark green filled circle** (#606C38) behind the icon or as a prominent indicator mark.
- **Icons:** Use thick-stroke (2px) monochromatic icons for high visibility.

### Badges & Chips
- **Alert Badges:** Use a **pink-red pill-shaped badge** (#F43F5E) with white text for urgent notifications or overdue statuses.
- **Status Chips:** Use secondary olive green for neutral infrastructure statuses.

### Buttons
- **Primary:** Vibrant green background (#10B981) with white text. Bold weight.
- **Secondary:** Transparent background with a 2px black border.

### Cards
- **Utility Cards:** Feature a 1px subtle border, `rounded-lg` corners, and a `body-md` title with `headline-md` data points.

### Map Elements
- **Satellite View:** For geographical utility tracking, use satellite-style tiles. 
- **Highlights:** Use **Golden (#F59E0B)** strokes or glows to highlight specific village sectors, pipes, or electrical nodes currently under selection or maintenance.

### Inputs
- **Text Fields:** White background, 1px black border when focused, and clear black labels positioned above the field.