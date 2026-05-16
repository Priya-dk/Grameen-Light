---
name: Grameen-Light
colors:
  surface: '#f7fbf1'
  surface-dim: '#d8dbd2'
  surface-bright: '#f7fbf1'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f2f5ec'
  surface-container: '#ecefe6'
  surface-container-high: '#e6e9e0'
  surface-container-highest: '#e0e4db'
  on-surface: '#191d17'
  on-surface-variant: '#41493e'
  inverse-surface: '#2d322c'
  inverse-on-surface: '#eff2e9'
  outline: '#717a6d'
  outline-variant: '#c0c9bb'
  surface-tint: '#2a6b2c'
  primary: '#00450d'
  on-primary: '#ffffff'
  primary-container: '#1b5e20'
  on-primary-container: '#90d689'
  inverse-primary: '#91d78a'
  secondary: '#795900'
  on-secondary: '#ffffff'
  secondary-container: '#fec330'
  on-secondary-container: '#6f5100'
  tertiary: '#6b1d3d'
  on-tertiary: '#ffffff'
  tertiary-container: '#883454'
  on-tertiary-container: '#ffaec6'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#acf4a4'
  primary-fixed-dim: '#91d78a'
  on-primary-fixed: '#002203'
  on-primary-fixed-variant: '#0c5216'
  secondary-fixed: '#ffdfa0'
  secondary-fixed-dim: '#f8bd2a'
  on-secondary-fixed: '#261a00'
  on-secondary-fixed-variant: '#5c4300'
  tertiary-fixed: '#ffd9e2'
  tertiary-fixed-dim: '#ffb1c8'
  on-tertiary-fixed: '#3e001d'
  on-tertiary-fixed-variant: '#7a2949'
  background: '#f7fbf1'
  on-background: '#191d17'
  surface-variant: '#e0e4db'
typography:
  headline-lg:
    fontFamily: Public Sans
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Public Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  title-lg:
    fontFamily: Public Sans
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Public Sans
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Public Sans
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-lg:
    fontFamily: Public Sans
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.1px
  label-md:
    fontFamily: Public Sans
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  touch-target-min: 48px
  margin-mobile: 16px
  gutter-mobile: 12px
  container-padding: 20px
---

## Brand & Style

The design system is engineered for civic reliability and high-utility monitoring in rural environments. It adopts a **Professional Gov-Tech** aesthetic, merging the structured principles of Material Design 3 with a clean, minimal interface that prioritizes clarity over ornamentation. 

The brand personality is authoritative yet accessible, designed to evoke a sense of public safety and technological advancement. By utilizing a high-contrast palette and generous touch targets, the design system ensures that village administrators and maintenance staff can navigate complex streetlight data with minimal cognitive load, even in varying outdoor lighting conditions.

## Colors

The color palette is rooted in a deep "Forest Green" to symbolize growth and sustainability, paired with a vibrant "Solar Yellow" for visibility and energy. 

The design system utilizes a semantic coloring strategy for streetlight status monitoring:
- **Success (Working):** Derived from the primary green, used for active, healthy lights.
- **Error (Fused):** A high-visibility red for immediate maintenance alerts.
- **Warning (Daytime Burning):** The secondary yellow, indicating a sensor malfunction or manual override.

In Dark Mode, primary colors are desaturated to meet AA accessibility standards against dark surfaces, ensuring the UI remains legible during night-time field inspections.

## Typography

The design system features **Public Sans**, a typeface designed for government interfaces. It provides a robust, neutral, and highly legible framework across all administrative levels.

- **Headlines:** Set with tight letter-spacing and bold weights to provide clear section anchoring.
- **Body Text:** Optimized for readability with a generous line-height to assist users who may be reading on low-resolution mobile devices.
- **Labels:** Used for data points and status chips, these utilize medium to semi-bold weights to ensure that critical technical data (such as voltage or pole IDs) is never missed.

## Layout & Spacing

The layout follows a **Fluid Grid** model optimized for the Android handheld experience. A standard 4-column grid is used for mobile portrait views, expanding to 8 columns for tablets.

- **Rhythm:** An 8px linear scale governs all padding and margins.
- **Touch Targets:** To accommodate field workers and diverse user demographics, all interactive elements (buttons, toggles, list items) maintain a minimum height of 48px.
- **Safe Areas:** A 16px horizontal margin is enforced globally to prevent content from touching screen edges.

## Elevation & Depth

Visual hierarchy in the design system is achieved through **Tonal Layers** and **Ambient Shadows**. Instead of harsh borders, surfaces are separated by subtle shifts in elevation.

- **Level 0 (Background):** Flat, used for the main canvas.
- **Level 1 (Cards):** Resting state for streetlight cards, featuring a soft 4% opacity black shadow with an 8px blur.
- **Level 2 (Active/Pressed):** Increased shadow spread (12px blur) to provide tactile feedback during interaction.
- **Sheet Elevation:** Modals and bottom sheets use a tinted backdrop blur (scrim) to maintain focus on the task at hand.

## Shapes

The shape language is consistently **Rounded**, reflecting the approachable nature of the service. 

- **Cards & Containers:** Use a 16px (rounded-lg) corner radius to create a friendly, modern container for technical data.
- **Buttons:** Fully pill-shaped or 8px rounded corners to distinguish them from informational cards.
- **Inputs:** A 4px radius is used for text fields to maintain a sense of structural stability and precision.

## Components

### Buttons
Primary actions use the Dark Green background with White text. Secondary actions use the Solar Yellow to draw attention to urgent field reports. All buttons utilize the 48px minimum height.

### Status Chips
Status chips are the core of the design system. They use a semi-transparent background of their semantic color (15% opacity) with a bold, high-contrast text label and a leading dot icon to ensure visibility for color-blind users.

### Streetlight Cards
Cards are the primary data vessel. They feature a clear title (Pole ID), a secondary label (Location), and a prominent status chip in the top right corner. Actionable icons for "Navigate" and "Report" are placed at the bottom for easy thumb access.

### Input Fields
Filled text fields are preferred over outlined ones for better visibility in sunlight. They include a thick 2px bottom stroke in the primary green when focused.

### Map Pins
Custom markers using the same status color logic (Green/Red/Yellow) with a high-contrast white border to pop against satellite imagery.