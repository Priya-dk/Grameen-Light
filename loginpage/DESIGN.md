---
name: Grameen-Light System
colors:
  surface: '#fdf8fd'
  surface-dim: '#ddd9de'
  surface-bright: '#fdf8fd'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f7f2f8'
  surface-container: '#f1ecf2'
  surface-container-high: '#ebe7ec'
  surface-container-highest: '#e5e1e7'
  on-surface: '#1c1b1f'
  on-surface-variant: '#40493d'
  inverse-surface: '#313034'
  inverse-on-surface: '#f4eff5'
  outline: '#707a6c'
  outline-variant: '#bfcaba'
  surface-tint: '#1b6d24'
  primary: '#0d631b'
  on-primary: '#ffffff'
  primary-container: '#2e7d32'
  on-primary-container: '#cbffc2'
  inverse-primary: '#88d982'
  secondary: '#795900'
  on-secondary: '#ffffff'
  secondary-container: '#fec330'
  on-secondary-container: '#6f5100'
  tertiary: '#005c81'
  on-tertiary: '#ffffff'
  tertiary-container: '#23759f'
  on-tertiary-container: '#e6f3ff'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#a3f69c'
  primary-fixed-dim: '#88d982'
  on-primary-fixed: '#002204'
  on-primary-fixed-variant: '#005312'
  secondary-fixed: '#ffdfa0'
  secondary-fixed-dim: '#f8bd2a'
  on-secondary-fixed: '#261a00'
  on-secondary-fixed-variant: '#5c4300'
  tertiary-fixed: '#c7e7ff'
  tertiary-fixed-dim: '#88cffd'
  on-tertiary-fixed: '#001e2e'
  on-tertiary-fixed-variant: '#004c6c'
  background: '#fdf8fd'
  on-background: '#1c1b1f'
  surface-variant: '#e5e1e7'
typography:
  display-lg:
    fontFamily: Inter
    fontSize: 57px
    fontWeight: '400'
    lineHeight: 64px
    letterSpacing: -0.25px
  headline-lg:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '400'
    lineHeight: 40px
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 28px
    fontWeight: 36px
  title-lg:
    fontFamily: Inter
    fontSize: 22px
    fontWeight: '500'
    lineHeight: 28px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
    letterSpacing: 0.5px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
    letterSpacing: 0.25px
  label-lg:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.1px
  label-md:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.5px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  margin-mobile: 16px
  margin-desktop: 24px
  gutter: 16px
  card-padding: 24px
---

## Brand & Style

The design system is engineered for "Grameen-Light," a platform dedicated to the digital stewardship of rural infrastructure. The brand personality strikes a precise balance between **Governmental Reliability** and **Startup Agility**. It communicates safety, efficiency, and modernization through a lens of accessibility.

The visual style is rooted in **Modern Corporate/Material Design 3**, utilizing a "Mobile-First" philosophy suitable for field technicians while remaining robust enough for administrative dashboards. The aesthetic is clean and functional, prioritizing high legibility and clear status communication (on/off/maintenance) through a sophisticated application of color and shape.

## Colors

The color palette leverages the Material 3 tonal system. 
- **Primary (Green):** Represents growth, sustainability, and "active" status for lighting systems.
- **Secondary (Yellow):** Used for accents, warnings, and low-priority notifications. It provides a warm contrast to the functional green.
- **Neutral (Dark Gray):** Serves as the foundation for typography and surface boundaries, ensuring high contrast for outdoor legibility.

In dark mode, the primary and secondary colors shift to lighter, desaturated tones to maintain AA/AAA accessibility standards against the deep gray backgrounds, reducing eye strain for night-shift operators.

## Typography

The design system utilizes **Inter** for all hierarchy levels to provide a systematic, neutral, and highly legible experience across both high-density data tables and mobile status updates. 

Headlines are used for navigation titles and critical hardware IDs. Body text is optimized for data-rich environments, ensuring that coordinates and technical specifications are easily scannable. Label styles are strictly reserved for button text, chips, and small metadata.

## Layout & Spacing

This design system follows a **Fluid Grid** model based on an 8dp square grid, consistent with Material 3 standards. 

- **Mobile:** Uses a 4-column grid with 16px side margins.
- **Tablet/Desktop:** Transitions to a 12-column grid with 24px side margins.
- **Spacing Rhythm:** All internal spacing between components (vertical and horizontal) should be multiples of 8px. Use 16px for related items and 24px or 32px to separate distinct sections or card containers.

## Elevation & Depth

Hierarchy is established through **Tonal Layers** and **Ambient Shadows**. This design system avoids harsh borders in favor of subtle surface tinting.

- **Level 0 (Surface):** The default background color.
- **Level 1 (Card/Container):** Uses a +1% Primary color overlay on the surface color with a subtle 2px blur shadow.
- **Level 2 (Active/Hover):** Higher tonal contrast and a 4px blur shadow.
- **Floating Action Buttons (FAB):** Utilize the highest elevation (Level 3) with a distinct 8px shadow to indicate priority interaction.

## Shapes

The shape language is defined by modern, large radii to soften the "industrial" nature of the product. 

- **Cards:** Use a **24px (1.5rem)** corner radius to create a containerized, friendly feel.
- **Buttons:** Fully rounded (pill-shaped) for primary actions.
- **Input Fields:** 8px (0.5rem) radius to maintain a professional, structured appearance for data entry.
- **Chips:** Fully rounded (pill-shaped) for status indicators like "Active" or "Faulty."

## Components

### Buttons
- **Primary:** Filled with the primary green, using white or high-contrast light green text.
- **Secondary:** Outlined with the primary green for less critical actions.
- **FAB (Floating Action Button):** A 56x56dp container with a 16px radius, utilizing the Secondary (Yellow) color to draw attention to "Add Device" or "Report Issue" actions.

### Cards
- Cards are the primary vessel for streetlight data. They must use the 24px rounded corners and Level 1 elevation. Internal padding is fixed at 24px to ensure content breathability.

### Input Fields
- Utilize the Material 3 "Filled" or "Outlined" style with a 4px bottom indicator. Labels must remain visible as floating text when the field is active.

### Status Chips
- **Operational:** Green container with dark green text.
- **Faulty:** Red/Error container with dark red text.
- **Maintenance:** Yellow container with dark brown/yellow text.

### Navigation
- **Mobile:** Bottom Navigation Bar with 4-5 key destinations (Map, Devices, Alerts, Settings).
- **Desktop:** Vertical Navigation Rail or Drawer on the left side to maximize vertical screen real estate for data tables.