# Test Charter Template

## Test objective
State the primary mission of the testing effort in one or two sentences.

Example:
Validate that the recent change to [feature/workflow/component] behaves correctly in the highest-risk user and system flows, with particular attention to regression risk, weak automation coverage, and known defect patterns.

---

## Change summary
Describe in plain language what changed and why it matters.

Include:
- what changed
- why it changed
- what kind of change it is
- which workflows are likely affected

---

## Scope
List what is in scope for this charter.

Examples:
- user workflows
- APIs
- services
- permissions
- integrations
- data states
- environments
- configurations

---

## Out of scope
List what is intentionally excluded.

Examples:
- unrelated modules
- low-risk downstream areas
- performance or accessibility unless specifically targeted
- non-impacted admin flows

---

## Evidence considered
Document the evidence used.

Examples:
- PR description
- diff
- commit messages
- review comments
- linked issue
- changed tests
- CI evidence
- coverage signals
- defect references

Also include missing evidence:
- no direct E2E coverage data
- defect history unavailable
- dependency breadth partly inferred

---

## Top risks
List the most important risks in descending order.

For each risk include:
- risk title
- risk level
- why it matters
- likely consequence if broken

Example:
### Shared validation behavior
Risk level: High  
Reason: reused in multiple workflows and recently changed  
Consequence: inconsistent behavior and business-critical user blocking

---

## Manual testing priorities
List what should be tested first.

For each priority include:
- area or workflow
- priority level
- reason

Example:
### Checkout submission after validation
Priority: Immediate  
Reason: revenue-critical flow, integration behavior changed, limited direct E2E evidence

---

## Preconditions and setup
List everything needed before testing starts.

Examples:
- environment
- user roles
- accounts
- feature flags
- seed data
- mocks or stubs
- service access
- logs or monitoring

---

## Scenario groups
Define the major manual test scenario groups.

For each group include:

### Scenario group title
Target:
Objective:
Risk addressed:
Recommended technique:
Priority:

Example:
### Boundary validation for amount limits
Target: transfer submission form  
Objective: validate min/max and out-of-range handling  
Risk addressed: incorrect acceptance or rejection near rule thresholds  
Recommended technique: boundary value analysis  
Priority: High

---

## Suggested execution order
List the recommended order for execution.

Example:
1. smoke and core happy path
2. highest-risk boundary cases
3. key integration paths
4. permission and role variants
5. regression around adjacent workflows
6. observability and error handling checks

---

## Observability and oracles
Document how the tester can tell whether behavior is correct.

Examples:
- visible UI outcome
- returned API response
- data stored in database
- emitted event
- audit log entry
- monitoring signal
- absence of unexpected side effects

---

## Assumptions and open questions
Capture uncertainty that may affect testing.

Examples:
- exact downstream consumers not fully confirmed
- test coverage evidence incomplete
- unclear whether mobile flow shares same backend path
- fallback behavior not explicitly described in PR

---

## Exit expectation
State what this charter should accomplish when completed.

Example:
By completing this charter, the tester should be able to provide evidence-based confidence about the safety of the change in the highest-risk workflows and identify any significant regression, coverage gap, or unresolved uncertainty.
