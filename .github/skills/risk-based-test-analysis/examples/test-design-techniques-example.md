# Example: Mapping Risks to Test Design Techniques

This example shows how to select appropriate test design techniques based on scenario shape rather than using all techniques at once.

## Scenario 1: Input amount range in payment form

### Risk
Amounts near minimum and maximum limits may be accepted or rejected incorrectly.

### Best technique
Boundary value analysis

### Why
The dominant risk is around thresholds and limits.

### Example focus
- exactly minimum
- just below minimum
- just above minimum
- exactly maximum
- just below maximum
- just above maximum

---

## Scenario 2: Country, customer type, and tax rule combinations

### Risk
Different combinations of country, user type, and exemption status may produce incorrect tax outcomes.

### Best technique
Decision table testing

### Why
The outcome depends on combinations of rule conditions.

### Example focus
- domestic individual
- domestic business
- foreign individual
- foreign business
- exempt versus non-exempt
- valid and invalid identifiers

---

## Scenario 3: Workflow from draft to submitted to approved to cancelled

### Risk
Certain actions may be available in the wrong status or blocked in the correct one.

### Best technique
State transition testing

### Why
Behavior depends on current and prior state.

### Example focus
- valid transitions
- invalid transitions
- repeated actions
- rollback or reversal handling

---

## Scenario 4: Many browser, locale, and feature-flag combinations

### Risk
The behavior may fail only in specific combinations, but exhaustive testing is too large.

### Best technique
Pairwise testing

### Why
The scenario involves many dimensions and combinations.

### Example focus
- browser
- locale
- account type
- feature flag state
- device class

---

## Scenario 5: Validation rules with many distinct input classes

### Risk
Different input categories may need different behavior, but testing each individual value is wasteful.

### Best technique
Equivalence partitioning

### Why
The main need is to represent each meaningful class of input.

### Example focus
- valid class
- invalid format class
- missing value class
- unsupported value class

---

## Scenario 6: Complex scenario space with multiple business dimensions

### Risk
The test space becomes hard to structure or communicate.

### Best technique
Classification tree method

### Why
It helps decompose a scenario into visible dimensions and representative combinations.

### Example focus
Dimensions such as:
- user role
- channel
- data state
- feature mode
- request type

---

## Scenario 7: Historically fragile area with recurring unusual defects

### Risk
Past issues show that real failures often come from awkward or non-obvious conditions.

### Best technique
Error guessing

### Why
Experienced intuition is useful when known fragility exists.

### Example focus
- repeated save clicks
- expired session during form submit
- stale browser state
- partial backend unavailability
- malformed pasted values

---

## Guidance
Choose techniques based on the scenario's dominant risk.

Good output should say:
- which technique is recommended
- why it fits
- what type of variation or behavior it should explore
