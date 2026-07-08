# The English Cut — Design Foundation

Companion to `Refactoring-Prompt.md`. Defines the visual/interaction system the refreshed storefront is built on — decided once here, inherited everywhere else. Scope: the customer- and admin-facing web UI only. Backend (Spring controllers/services/entities, DB schema) is out of scope and unaffected.

> Not to be confused with the repo-root `design-foundation.md`, which belongs to an unrelated personal portfolio project. This file follows the same structure/format by request, but the content below is specific to this storefront's brand.

## Style direction
Artisan/premium food-market feel — this is a butcher/delicatessen storefront (Iberian ham, seafood, croquetas), not a generic Bootstrap admin panel. Warm, tactile, editorial: think a high-end deli's website, not a SaaS dashboard. Confident typography, product photography given room to breathe, minimal chrome.

## Theme
Light by default (food photography reads best on a light, warm-neutral background). Optional dark mode is a nice-to-have, not a launch requirement — the current app has none.

## Color tokens

| Token | Value | Usage |
|---|---|---|
| Background | `#FBF7F2` | page background |
| Surface | `#FFFFFF` | product cards, panels, modals |
| Surface — sunken | `#F1EAE0` | table stripes, input backgrounds |
| Text primary | `#2B2320` | headings, body copy |
| Text secondary | `#75695F` | captions, metadata, helper text |
| Accent — primary | `#7A2E20` | buttons, links, active nav, primary CTAs (butcher-red) |
| Accent — secondary | `#B98A2E` | badges, prices, stock/quantity highlights |
| Success | `#3F7A4E` | delivery-completed states, in-stock indicators |
| Danger | `#B23A2E` | out-of-stock, delete actions, error banners |

Bootstrap's default blue/gray palette (currently used untouched throughout) is retired entirely — no `btn-primary` blue, no `bg-dark` navbar.

## Typography
- **Display** (headings, product names): Fraunces or Playfair Display — a serif with character, signals "artisan" rather than "template"
- **Body** (paragraphs, UI text, forms): Inter
- **Utility/mono** (prices, quantities, order IDs, timestamps): JetBrains Mono

Type scale, weights, line-height set once as tokens — never per-page inline styles (the current JSPs have several one-off inline `style="..."` attributes on carousel images and cards that should not survive the refactor).

## Icons
Outline/line style, one library throughout (Lucide). Replace the current ad-hoc PNGs (`iconos/bin.png`, `carros.png`, `info.png`, `usuario.png`, `patatas.png`) with icon components — those PNGs are low-res and visually inconsistent with each other.

## Motion
Subtle only:
- Fade-up on scroll for section entrances (product grids, order tables)
- Quiet hover states on cards/buttons (slight lift + shadow, no color-inversion tricks)
- No auto-playing carousels — the current home/product-detail Bootstrap carousels auto-rotate; replace with user-controlled or static layouts
- Respect `prefers-reduced-motion`

## Layout rules
- Product grid: responsive card grid (auto-fill, min card width ~260px), not the fixed `row-cols-xxl-4` Bootstrap grid currently hardcoded
- Base spacing unit: 8px
- Section padding: ~64px+ desktop, ~32px mobile
- Sticky top nav, collapses to a hamburger + slide-over on mobile; cart icon and login/user state always visible regardless of collapse state (currently the cart badge and login/logout buttons are buried inside the collapsing navbar-collapse div — that's a mobile UX regression)

## Content rules
- No placeholder copy ships: current templates contain literal filler ("Card title", "Some quick example text...", "HOLA MUNDO", "Go somewhere", an empty `<title>Document</title>` on the footer partial, generic Bootstrap-docs footer links "Features/Pricing/FAQs"). Every page in the refreshed UI must have real copy or a clearly-marked CMS/data-driven slot.
- Product images referenced by bare filename (`../../img/productos/<name>`) from Java-side `getImage()` — refresh should keep this same contract (filename in, resolved URL out) so the backend truly stays untouched.

## Quality floor (non-negotiable regardless of page)
- Fully responsive down to mobile (current pages have zero mobile testing evidence — e.g. product-detail's three-column `col / col-8 / col` layout has no stacking breakpoint)
- Visible keyboard focus states (none currently — relying on Bootstrap defaults only)
- Reduced motion respected
- Consistent spacing/type via shared tokens, not the current mix of Bootstrap utility classes + inline `style=` + ad-hoc CSS files per page
