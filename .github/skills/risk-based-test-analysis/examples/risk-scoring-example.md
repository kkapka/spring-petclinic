# Example: Risk Scoring

This example shows how to apply the scoring model in a transparent and lightweight way.

## Risk factors
Score each factor from 0 to 3.

- Business criticality
- Technical complexity
- Impact breadth
- Integration exposure
- Failure consequence
- Change uncertainty
- Automation weakness

### Score guide
- 0 = negligible or not evident
- 1 = limited
- 2 = meaningful
- 3 = strong or severe

---

## Example area 1: Checkout submission after discount recalculation

### Observations
- user-facing purchase flow
- affects pricing and final submission path
- touches shared pricing service and checkout API
- review comments mention edge cases around voucher stacking
- only unit tests were clearly updated

### Scoring
- Business criticality: 3
- Technical complexity: 2
- Impact breadth: 2
- Integration exposure: 3
- Failure consequence: 3
- Change uncertainty: 2
- Automation weakness: 2

Total score: 17  
Risk level: Critical

### Explanation
This is a critical revenue-facing workflow with cross-component impact and meaningful integration exposure. Failure would be highly visible and costly. Review discussion and limited visible integration evidence further increase risk.

---

## Example area 2: Admin label rename in settings page

### Observations
- internal UI only
- no logic change
- no shared business rules involved
- snapshot tests updated
- no meaningful cross-component impact

### Scoring
- Business criticality: 0
- Technical complexity: 0
- Impact breadth: 0
- Integration exposure: 0
- Failure consequence: 1
- Change uncertainty: 0
- Automation weakness: 0

Total score: 1  
Risk level: Low

### Explanation
This appears isolated, low-complexity, and low-consequence. Minimal manual testing may be enough.

---

## Example area 3: Shared authentication token refresh handling

### Observations
- shared cross-session behavior
- affects login continuity and API access
- async timing and retry behavior involved
- multiple consumers depend on token validity
- difficult to detect all failure modes through unit tests alone

### Scoring
- Business criticality: 3
- Technical complexity: 3
- Impact breadth: 3
- Integration exposure: 2
- Failure consequence: 3
- Change uncertainty: 2
- Automation weakness: 2

Total score: 18  
Risk level: Critical

### Explanation
This area combines high complexity, broad dependency impact, and difficult failure detectability. It is a strong candidate for focused manual testing and exploratory checks.

---

## Guidance
Use the score to support consistency, not to replace judgment.

Always explain:
- what drove the score
- which factors mattered most
- where evidence was weak or inferred

Do not rank purely by line count or file count.
