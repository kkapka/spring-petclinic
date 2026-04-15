# Risk-Based Test Analysis Skill Package

This package contains a GitHub Copilot skill for structured, risk-based PR test analysis.

It is designed to help Copilot analyze one or more pull requests in a deterministic way and produce:

- a change summary
- affected areas and dependencies
- a risk ranking
- coverage and defect signals
- manual testing priorities
- suggested test scenarios
- a final tester-ready test charter

## Folder structure

```text
.github/
  skills/
    risk-based-test-analysis/
      SKILL.md
      examples/
        multi-pr-analysis-example.md
        pr-analysis-output.md
        risk-scoring-example.md
        test-charter-template.md
        test-design-techniques-example.md
```

## Installation

Copy the `.github` folder into the root of your repository.

## What this skill is for

Use this skill when you want Copilot to do more than just generate test cases.

This skill is meant to guide Copilot through a full QA analysis flow:

1. understand the change
2. identify impacted components and workflows
3. assess risk
4. review coverage and defect signals
5. prioritize manual testing
6. derive scenario groups
7. produce a test charter

## How skill activation works

Copilot generally decides whether to use the skill based on the user prompt and the skill description in `SKILL.md`.

For best results, prompts should clearly match the skill intent. Avoid vague prompts like:

- `review this PR`
- `make a test charter`
- `what should I test?`

Instead, ask for the ordered workflow and the expected output sections.

## Usage

### Recommended safe default

Use this when you want the full workflow with a short prompt.

```text
Use the risk-based test analysis workflow for PR #123.

Follow the workflow strictly and do not skip steps. Summarize the change, identify directly and indirectly affected components and dependencies, rank the key risks, assess coverage and defect signals, derive prioritized manual test scenario groups, and finish with a tester-ready test charter.

Return the result in these sections: Change Summary, Evidence Used, Affected Areas, Risk Ranking, Coverage and Defect Signals, Manual Testing Priorities, Suggested Test Scenarios, and Test Charter.
```

Why use this:
- best default for everyday PR analysis
- short enough for regular use
- strong enough to reduce shallow output

### Deterministic variant

Use this when consistency matters most and you want to reduce variance between runs.

```text
Use the risk-based test analysis workflow for PR #123.

Follow the workflow strictly and do not skip, merge, or reorder steps. Do not jump directly to the final test charter. If evidence is missing, state that explicitly instead of guessing.

Perform these steps in order:
1. Summarize the change in plain language.
2. Identify directly affected components.
3. Identify indirectly affected components, dependencies, integration points, and impacted workflows.
4. Rank the main risks and explain the strongest contributing factors.
5. Assess coverage and defect signals, including uncertainty and missing evidence.
6. Derive prioritized manual test scenario groups.
7. Select the most appropriate structured test design techniques for each important scenario group and explain why they fit.
8. Produce a final tester-ready test charter.

Return the result in exactly these sections:
1. Change Summary
2. Evidence Used
3. Affected Areas
4. Risk Ranking
5. Coverage and Defect Signals
6. Manual Testing Priorities
7. Suggested Test Scenarios
8. Test Charter
```

Why use this:
- best for team-wide standardization
- best when deterministic output matters
- best when you do not want Copilot to skip intermediate analysis

### Recommendation in 2 to 3 sentences

If you want one default prompt for normal use, use the recommended safe default. It is compact, still pushes Copilot through the full skill flow, and usually gives a good balance between guidance and usability. If output consistency is critical across users and runs, use the deterministic variant.

## When to use which prompt style

### Use the recommended safe default when
- you want the full workflow
- you want a practical day-to-day prompt
- you want strong guidance without too much verbosity

### Use the deterministic variant when
- results vary too much between runs
- multiple team members should get similar output structure
- you need strict step order and section completeness
- you do not want Copilot to jump directly to the final charter

### Use a partial prompt when
- you only need one part of the workflow
- you already performed some of the analysis manually
- you want a faster, narrower output for a specific purpose

## Partial-use prompt examples

### Only change summary

```text
Use the risk-based test analysis workflow for PR #123, but only perform the change understanding part.

Summarize the change in plain language, identify the change type, and explain which main workflows or system areas appear affected. Do not generate risk ranking, test scenarios, or a test charter.
```

Use this when:
- you want a quick briefing
- you need to align stakeholders first
- you are preparing for a review meeting

### Only affected areas and dependencies

```text
Use the risk-based test analysis workflow for PR #123, but only identify affected areas.

List directly affected components, indirectly affected components, impacted workflows, and important dependencies or integration points. Clearly distinguish observed impact from inferred impact. Do not create a test charter.
```

Use this when:
- you want impact analysis only
- you are mapping regression scope
- you want to understand blast radius before planning tests

### Only risk analysis

```text
Use the risk-based test analysis workflow for PR #123, but focus only on risk analysis.

Based on the available PR context, identify the most important risk areas, score them using the skill's risk factors where possible, explain the strongest contributing factors, and return only Change Summary, Evidence Used, Affected Areas, and Risk Ranking.
```

Use this when:
- you only need a risk view
- you want fast release triage
- you are deciding test depth or go/no-go focus

### Only manual testing priorities

```text
Use the risk-based test analysis workflow for PR #123, but focus only on what should be tested manually.

Review the change, affected areas, and any visible coverage or defect signals, then return Manual Testing Priorities and a short supporting rationale. Do not generate a full charter.
```

Use this when:
- you need quick QA direction
- you are time-constrained
- you only want a prioritized testing shortlist

### Only scenario groups and techniques

```text
Use the risk-based test analysis workflow for PR #123, but generate only prioritized manual test scenario groups.

Base the result on the PR change context and return Suggested Test Scenarios with recommended structured test design techniques and short explanations for why each technique fits.
```

Use this when:
- you already know the risk areas
- you want help designing tests
- you need scenario ideas, not a full report

### Only final test charter

```text
Use the risk-based test analysis workflow for PR #123 and create only the final Test Charter.

Before writing the charter, still perform the internal analysis steps of change understanding, affected area identification, risk ranking, and manual testing prioritization. In the visible output, return only the final Test Charter.
```

Use this when:
- you want a compact deliverable
- you do not need the intermediate sections shown
- you still want the charter to be informed by the full workflow

## Single PR and multi-PR examples

### Single PR full analysis

```text
Use the risk-based test analysis workflow for PR #123.

Follow the workflow strictly and do not skip steps. Summarize the change, identify impacted areas and dependencies, rank the risks, assess coverage and defect signals, derive prioritized manual test scenarios, and finish with a tester-ready test charter.
```

### Multiple PRs as one release set

```text
Use the risk-based test analysis workflow for PRs #123, #124, and #125 as one combined release set.

Follow the workflow strictly and do not skip, merge, or reorder steps. Consider both individual PR risk and combined cross-PR workflow impact. Return the result in these sections: Change Summary, Evidence Used, Affected Areas, Risk Ranking, Coverage and Defect Signals, Manual Testing Priorities, Suggested Test Scenarios, and Test Charter.
```

### Bugfix-focused version

```text
Use the risk-based test analysis workflow for PR #123 with a bugfix focus.

Follow the workflow strictly and do not skip, merge, or reorder steps. Pay special attention to regression risk, edge cases, and whether the fix could affect adjacent workflows. Return the result in the standard analysis sections and finish with a tester-ready test charter.
```

## Practical guidance for users

### Good prompting habits
- mention PR number or PR set clearly
- ask for the ordered workflow, not only the final artifact
- ask for the standard output sections when consistency matters
- explicitly say when evidence gaps should be surfaced
- explicitly say when you only want a partial result

### Avoid these prompt patterns
- `make me a test charter`
- `what should QA test?`
- `look at this PR`
- `give me test cases`

These prompts may still trigger the skill, but they are more likely to produce shallow or inconsistent output.

## Included example files

- `examples/pr-analysis-output.md` for a realistic single-PR output
- `examples/test-charter-template.md` for a reusable charter structure
- `examples/risk-scoring-example.md` for the scoring model
- `examples/test-design-techniques-example.md` for mapping scenario types to techniques
- `examples/multi-pr-analysis-example.md` for combined release-set analysis

## Suggested team usage

If several people use this skill, define one approved default prompt and one approved deterministic prompt. This reduces variability and makes results easier to compare across PRs and releases.
