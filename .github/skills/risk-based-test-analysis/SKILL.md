---
name: risk-based-test-analysis
description: Analyze pull requests and related engineering context to identify impacted areas, assess change risk, evaluate coverage and defect signals, and produce a prioritized manual testing charter with suggested scenarios and structured test design techniques.
---

# Risk-Based Test Analysis

## Purpose

Use this skill to analyze one or more pull requests and turn change context into a risk-based manual testing strategy.

This skill is designed for situations where the goal is not just to describe code changes, but to determine:

- what changed in practical terms
- which components, workflows, and dependencies were affected
- which areas carry the highest delivery and regression risk
- where automation or quality signals are weak
- what a manual tester should validate first
- which scenario design techniques are most appropriate

The primary outcome is a **tester-ready manual test charter** supported by a transparent analysis.

---

## When to use

Use this skill when you need to:

- analyze one or more pull requests from a QA or test analysis perspective
- understand direct and indirect impact of code changes
- identify high-risk regression areas
- determine manual testing priorities
- derive focused test scenarios instead of generic test case lists
- create a structured test charter for a manual tester or test engineer
- review whether available test evidence appears proportionate to change impact

Typical prompts include:

- Analyze this PR and tell me what should be tested manually
- Review these PRs and create a risk-based test charter
- Which parts of this change are highest risk?
- What testing should QA prioritize for these changes?
- Which workflows and components are most likely to be affected?
- Suggest manual test scenarios using structured test design techniques

---

## What good output looks like

A strong result from this skill should help a tester or reviewer answer:

- What changed, in plain language?
- Which areas were affected directly, indirectly, and systemically?
- Which risks matter most, and why?
- Where are automation and quality signals weak or uncertain?
- What should be tested manually first?
- Which scenario groups are worth executing?
- Which test design techniques fit those scenarios?
- What is the exact testing mission?

The output must be practical, explainable, and prioritized.

---

## Core operating principles

Always follow these principles.

### 1. Do not rely on the diff alone
Use as much context as possible, including:

- PR title and description
- changed files and diff
- commit messages
- review comments
- discussion comments
- linked issues or defects
- changed test files
- CI evidence
- repository structure and dependency context

A diff shows what changed, but not necessarily why it changed, what was debated, or where uncertainty remains.

### 2. Separate change risk from manual test priority
These are related but not identical.

A change may be technically risky but already strongly protected by automation.  
A change may be moderate in technical complexity but still deserve high manual testing priority due to poor coverage, historical instability, or user-facing criticality.

Always evaluate both.

### 3. Distinguish observed impact from inferred impact
Make it clear whether a conclusion is based on:

- directly observed code changes
- explicitly referenced dependencies or workflows
- plausible but inferred impact based on architecture or coupling

Do not present inference as fact.

### 4. Prefer explainability over false precision
Scores are helpful only when they remain understandable.  
Always explain why an area is risky or why it deserves manual focus.

### 5. Focus on decision value
The goal is not to generate the largest number of test ideas.  
The goal is to help a tester spend effort where it matters most.

### 6. Use structured test design techniques intentionally
Select techniques that match the nature of the risk or scenario.  
Do not mechanically list all techniques.

### 7. Be explicit about missing evidence
If review comments, defect history, coverage signals, or dependency data are missing or incomplete, state that clearly.

---

## Inputs and evidence sources

Gather and use as many of the following as are available.

### Change context
- PR title
- PR description
- commit list
- commit messages
- changed files
- code diff
- linked PRs if relevant

### Review and intent context
- review comments
- discussion comments
- unresolved questions
- linked issues, bugs, tickets, or requirements
- referenced design notes or docs if available

### Test and quality context
- changed test files
- CI results
- test failures or flaky signals if visible
- unit, integration, or end-to-end coverage signals if available
- defect references or known issues in the affected area

### Structural context
- package/module boundaries
- service boundaries
- shared libraries
- API or event contracts
- access control or permissions model
- data flow or persistence touchpoints

If some of these are missing, continue with best effort and explicitly record the gap.

---

## Required workflow

Follow the workflow in this order.

## Step 1: Understand the change

Start by explaining the change in plain language.

Determine as clearly as possible:

- what was changed
- why it was changed
- what kind of change it is:
  - feature
  - bug fix
  - refactor
  - migration
  - configuration change
  - test-only change
  - mixed change
- whether it affects:
  - user-facing behavior
  - backend logic
  - API contracts
  - validation
  - data persistence
  - state handling
  - async behavior
  - permissions
  - integrations
  - observability or operational behavior
- which assumptions, concerns, or tradeoffs appeared in the review discussion

### Output of this step
Produce a concise **Change Summary** before moving on.

---

## Step 2: Identify affected areas

Identify affected areas at three levels.

### Directly affected
These are explicitly changed parts of the system, for example:

- modules
- classes
- services
- functions
- UI components
- APIs
- configuration
- schemas
- queries
- database logic
- tests

### Indirectly affected
These are consumers, callers, dependencies, or coupled behaviors that may be influenced, for example:

- downstream consumers
- upstream callers
- shared libraries
- reused validators
- common utility layers
- event subscribers or publishers
- data contract consumers
- feature flag behavior
- role and permission checks

### Systemically affected
These are broader surfaces likely to carry regression risk, for example:

- end-to-end business workflows
- cross-component flows
- cross-service interactions
- user journeys
- error handling
- fallback behavior
- retries, idempotency, or timing behavior
- logging, metrics, or monitoring visibility

### Important rule
Do not stop at the changed file list.  
Consider propagation through dependency chains, shared logic, interfaces, and operational workflows.

### Output of this step
Produce a structured **Affected Areas** section with:

- directly affected components
- indirectly affected components
- impacted workflows
- important dependencies and integration points

Clearly label what is observed versus inferred.

---

## Step 3: Assess change risk

Assess the risk of each major affected area or use case.

Use the following risk factors.

### Risk factors
Evaluate each area against these factors on a simple scale such as 0 to 3.

#### A. Business criticality
How important is the affected behavior to core business use, revenue, compliance, trust, or critical operations?

#### B. Technical complexity
How difficult is the change to reason about correctly?  
Consider branching logic, hidden coupling, async behavior, statefulness, concurrency, data mutation, or complex rules.

#### C. Impact breadth
How many components, modules, services, or workflows are affected directly or indirectly?

#### D. Integration exposure
Does the area touch service boundaries, external dependencies, APIs, events, persistence, or cross-layer contracts?

#### E. Failure consequence
If this breaks, how serious is the likely outcome?  
Consider user harm, operational disruption, data corruption, business interruption, or difficult recovery.

#### F. Change uncertainty
Did comments, review discussion, or implementation choices indicate ambiguity, concern, tradeoffs, or fragile assumptions?

#### G. Automation weakness
Is automated protection missing, limited, unclear, or disproportionate to the risk?

### Suggested scoring model
For each area:

- score each factor from 0 to 3
- sum the values for a total risk score
- map the result to a risk level

Example mapping:

- 0 to 5 = Low
- 6 to 10 = Medium
- 11 to 15 = High
- 16 to 21 = Critical

Do not treat this as mathematically exact.  
It is a consistency aid, not a scientific model.

### Required explanation
For each ranked area, include:

- risk level
- optional total score
- a short explanation of the strongest contributing factors

Example rationale style:

- shared validation logic reused across multiple entry points
- touches 7 components and 2 integration boundaries
- state transition behavior changed
- review comments flagged uncertainty around backward compatibility
- limited evidence of integration coverage

### Output of this step
Produce a **Risk Ranking** of the most important areas.

---

## Step 4: Assess manual test priority

Now evaluate manual test priority separately from raw change risk.

A change may be high risk but lower manual priority if automated evidence is strong.  
A moderate-risk change may deserve very high manual focus if coverage is weak and the workflow is critical.

### Manual test priority factors
Evaluate these factors:

#### A. Change risk
Use the previous step as an input, not the only answer.

#### B. Unit test confidence
Is there evidence that logic-level behavior is well checked?

#### C. Integration test confidence
Is there evidence that cross-component behavior is protected?

#### D. End-to-end confidence
If relevant, is there evidence that real workflow behavior is protected?

#### E. Defect history
Have related components or workflows shown repeated instability or important defects?

#### F. Observability gap
Would failures be difficult to catch automatically, quickly, or reliably?

#### G. User-facing sensitivity
Would a problem likely be visible, disruptive, confusing, or trust-damaging to users?

### Priority levels
Assign manual testing priority such as:

- Low
- Medium
- High
- Immediate

### Required explanation
For each manually prioritized area, explain why it deserves manual attention.

Example:

- medium technical risk, but weak integration evidence and highly user-visible workflow
- high-risk change, but strong unit and integration coverage reduce immediate manual priority
- moderate impact, but defect history and detectability concerns justify focused exploratory testing

### Output of this step
Produce a **Manual Testing Priorities** section.

---

## Step 5: Derive scenario groups

Only after analyzing risk and manual testing priority, derive test scenario groups.

Scenarios should be:

- targeted
- non-redundant
- tied to a specific risk or workflow
- concrete enough for execution planning
- grouped logically

### Scenario categories to consider
Consider relevant categories such as:

- primary happy paths
- alternate or secondary paths
- validation behavior
- negative cases
- boundary and threshold behavior
- workflow transitions
- role and permission variants
- integration interactions
- error handling and fallback behavior
- data persistence and consistency
- regression around neighboring features
- compatibility or configuration-specific behavior
- recovery and retry behavior where relevant

### Important rule
Prefer meaningful scenario groups over a long flat list of generic test cases.

### Output of this step
Produce a **Suggested Test Scenarios** section grouped by theme or workflow.

---

## Step 6: Apply structured test design techniques

For each important scenario or scenario group, choose the most appropriate test design technique or techniques.

### Supported techniques
- equivalence partitioning
- boundary value analysis
- decision table testing
- state transition testing
- pairwise testing
- classification tree method
- use-case-based testing
- error guessing

### Technique selection guidance

#### Use equivalence partitioning when
Inputs can be grouped into behaviorally similar classes.

#### Use boundary value analysis when
Thresholds, limits, min/max rules, ranges, counts, dates, lengths, or sizes matter.

#### Use decision table testing when
Outcomes depend on combinations of business rules or conditions.

#### Use state transition testing when
Behavior depends on status changes, lifecycle stages, or previous system state.

#### Use pairwise testing when
There are many combinations of parameters, configurations, or inputs and exhaustive testing is unrealistic.

#### Use classification tree method when
A scenario space needs structured decomposition across several dimensions.

#### Use use-case-based testing when
End-to-end user or business flows are central.

#### Use error guessing when
Known defect patterns, implementation fragility, or reviewer concerns justify experienced intuition.

### Important rule
Do not list techniques without linking them to specific scenarios.

### Output of this step
For each scenario group, include:

- the recommended technique
- why that technique fits the scenario

---

## Step 7: Produce the final test charter

The final deliverable must be practical for a manual tester.

The charter should be concise but operationally useful.

### Required sections

#### Test objective
What this test effort is intended to validate.

#### Change summary
What changed in practical terms.

#### Scope
What components, workflows, integrations, or roles are in scope.

#### Out of scope
What is intentionally excluded from this test mission.

#### Top risks
The most important risks and why they matter.

#### Execution priorities
What to test first, second, and later.

#### Preconditions and setup
Required environment, test data, user roles, feature flags, mocks, configuration, logging access, or monitoring access.

#### Suggested scenario groups
A prioritized list of manual test scenario groups.

#### Recommended test design techniques
Technique guidance linked to the scenarios.

#### Coverage and defect observations
Signals that increase or reduce confidence.

#### Assumptions and open questions
Anything the tester should know before execution.

### Final objective
The test charter should allow a test engineer to begin manual testing with clear focus and rationale.

---

## Output format

Use the following output format unless the user requests another structure.

## 1. Change Summary
A concise plain-language description of the change and its intent.

## 2. Evidence Used
List the evidence used in the analysis, for example:

- PR description
- diff
- commit messages
- review comments
- linked issue
- changed tests
- CI evidence
- coverage signal
- defect references

Also list key missing evidence if applicable.

## 3. Affected Areas
Organize into:

- Directly affected components
- Indirectly affected components
- Impacted workflows
- Dependencies and integration points

Label observed versus inferred impact.

## 4. Risk Ranking
For each major area include:

- area or workflow
- risk level
- optional score
- strongest contributing factors
- likely consequence if broken

Sort from highest to lowest importance.

## 5. Coverage and Defect Signals
Summarize available quality signals such as:

- unit coverage confidence
- integration coverage confidence
- end-to-end coverage confidence if known
- changed or missing tests
- defect history or known issues
- uncertainty due to missing evidence

## 6. Manual Testing Priorities
For each prioritized area include:

- area or workflow
- manual priority
- reason for manual focus

## 7. Suggested Test Scenarios
For each scenario group include:

- title
- target component or workflow
- objective
- risk addressed
- recommended technique
- priority

## 8. Test Charter
Provide a tester-facing summary with:

- test objective
- scope
- out of scope
- top risks
- execution order
- setup and preconditions
- scenario groups
- assumptions and open questions

---

## Additional guidance for judgment

### Do not overvalue file count
A small change can be very risky if it affects:

- shared business logic
- authorization
- financial logic
- data integrity
- contract compatibility
- workflow state transitions
- failure handling
- critical user paths

### Do not overtrust coverage
High coverage does not automatically mean low risk.  
Coverage may be shallow, narrow, or not representative of realistic behavior.

### Do not underweight discussion context
Reviewer concerns, tradeoffs, and unresolved questions are important signals.

### Do not ignore indirect impact
Regression often appears in adjacent consumers, workflows, or contracts, not only in edited files.

### Do not generate exhaustive scripts by default
Start with scenario-level guidance and a strong test charter unless the user explicitly asks for fully detailed step-by-step manual test cases.

---

## Handling uncertainty

If evidence is incomplete, say so explicitly.

Use phrases such as:

- based on available evidence
- no direct coverage data was available
- defect history was not visible in the provided context
- dependency impact is partly inferred from module structure
- review discussion suggests possible uncertainty in this area

This improves trustworthiness.

---

## Quality bar

A good answer produced with this skill should be:

- specific to the actual change
- grounded in evidence
- transparent about assumptions
- useful for a manual tester
- prioritized by risk and confidence gaps
- structured and concise
- strong enough to support action without unnecessary filler

---

## Final instruction

Optimize for testing usefulness.

Your final answer should help a test engineer, QA reviewer, or delivery owner understand where the most important manual testing effort should go, why it should go there, and how to approach that testing with disciplined scenario design.
