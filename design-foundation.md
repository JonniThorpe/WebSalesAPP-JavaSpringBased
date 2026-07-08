# jonatanthorpe.dev — Design Foundation

Companion to `portfolio-sections-spec.md`. This defines the visual/interaction system every section should be built on top of — decide once here, inherit everywhere else.

## Style direction
Minimal/clean. Lots of whitespace, neutral tones, restrained accents. Corporate-leaning (primary audience: consulting/corporate recruiters), but with genuine personal voice — not generic "passionate developer" templating.

## Theme
Defaults to visitor's system preference (light/dark), with manual toggle available.

## Color tokens

| Token | Light | Dark | Usage |
|---|---|---|---|
| Background | `#FAFAF8` | `#17181A` | page background |
| Surface | `#F1F0EC` | `#1F2023` | cards, section panels |
| Text primary | `#1C1B1A` | `#EDEDEB` | body copy, headings |
| Text secondary | `#6B6862` | `#9B9892` | captions, metadata |
| Accent — primary | `#2B4C43` | `#4C8577` | links, buttons, interactive/nav elements |
| Accent — secondary | `#D2601A` | `#F08A4B` | subsection headers, callouts, remarkable info — used sparingly, never as large fills |

## Typography

- **Display** (headings): Sora
- **Body** (paragraphs, UI text): Inter
- **Utility/mono** (labels, dates, stack tags, small data points): JetBrains Mono

Type scale, weights, and line-height should be set once as design tokens (not per-section overrides).

## Icons
Outline/line style, single consistent library (Lucide recommended). No mixing icon sources.

## Motion
Subtle only:
- Fade-in / fade-up on scroll for section entrances
- Quiet hover states on interactive elements (buttons, links, cards)
- No noticeable/heavy scroll animations, no parallax, no auto-playing decorative motion
- Respect `prefers-reduced-motion`

## Layout rules
- Single column, max content width ~760px for text-heavy sections
- Base spacing unit: 8px
- Section padding: ~96px+ desktop, ~48px mobile
- Sticky, minimal text-only navbar (scrollspy-highlighted current section); collapses to hamburger on mobile

## Quality floor (non-negotiable regardless of section content)
- Fully responsive down to mobile
- Visible keyboard focus states
- Reduced motion respected
- Consistent spacing/type applied via shared tokens, not repeated inline values
