---
description: Update documentation, progress tracker, and README (optionally create detailed learning guide)
---

# Update Documentation & Progress

Optional flag: $ARGUMENTS (e.g. `--detailed-learning` to force a detailed learning file)

Update all documentation for the current problem and sync progress across files.

**Rule 0 applies (see root `CLAUDE.md`): never touch the solution code itself.**

## Do

- Update JavaDoc in the problem file with:
  - Final solution approach.
  - Time and space complexity.
  - Key learnings and alternative approaches.
  - Do NOT touch the solution code itself (Rule #0).
  - If the problem is complex, reference a detailed learning file (e.g., `Detailed Learning Guide: See arraysAndHashing/learnings/ProblemName-Learning.md`).
- Update `PROGRESS.md`:
  - Mark problem as ✅ Done in the Overview Table (fill in complexity, AI help).
  - Complete the Daily Progress Log entry with approach, complexity, learnings, AI status.
  - Update category checklist.
  - Add new entries to Key Learnings and Patterns Identified sections.
  - Update Summary Statistics (total solved, in progress, streak).
- Update `README.md`:
  - Update category table count.
  - Update statistics section.
  - Update project structure if new folders added.
- **Update `SKILLS.md`** — for each concept detected in `/analyse` (or detect now if not yet run):
  - **If concept is NEW (first time):**
    - Add new row to main Skills Tracking Table.
    - Set "First Used" to current date, "First Problem" to current problem name/number, "Times Used" to 1.
    - Set "Usage History" to `Problem Name (#Number) (YYYY-MM-DD)`.
    - Add to appropriate category breakdown table (Data Structures/Algorithms/Patterns/Optimization).
    - Add to "Recently Learned (Last 5)" list at top.
    - Assign mastery level: 🌱 Beginner (1-2 uses).
  - **If concept EXISTS (reusing):**
    - Update "Times Used" count (+1).
    - Append to "Usage History": `, Problem Name (#Number) (YYYY-MM-DD)` (comma-separated, chronological).
    - Update "Latest Use" in category breakdown.
    - Update mastery level if threshold crossed: 🌱 Beginner (1-2), 🌿 Intermediate (3-5), 🌳 Proficient (6-10), 🏆 Master (11+).
    - Update "Top 5 Most Used Concepts" if ranking changes.
  - Update Quick Stats section: Total Concepts Mastered, Most Used Concept (name and count), Recently Learned (last concept added), Average Reuse Rate (total uses / total concepts).
  - Update Mastery Levels section with counts per level.
  - Update "Last tracking update" timestamp at bottom.

### Concept Detection (if `/analyse` not run yet)

Run the same concept detection logic as `/analyse`: scan the user's solution code for data structures, algorithms, patterns, optimizations. Ignore AI-generated test/infrastructure code. Focus only on what the USER implemented in the solution method.

### Format for Usage History

- Single line, comma-separated.
- Example: `Two Sum (#1) (2026-02-13), Group Anagrams (#49) (2026-02-13), Product Array (#238) (2026-02-18)`.
- Keep chronological order (oldest first, newest last).

## Optional: Create Detailed Learning File

When the user requests "create detailed learning" / "generate learning file", or the problem demonstrates significant complexity:

1. Create `<category>/learnings/` folder if it doesn't exist.
2. Create `<ProblemName>-Learning.md` with comprehensive analysis:
   - **Problem Evolution Journey:** all attempts and iterations.
   - **Solution Comparison:** user's solution vs alternatives (theory vs practice).
   - **Performance Deep Dive:** why certain O() beats others (constant factors, JVM optimizations).
   - **Key Insights:** theory vs practice gaps, hidden costs, optimization paradoxes.
   - **Major Lessons:** Big-O limitations, constant factors, JVM intrinsics, data structure tradeoffs.
   - **Best Practices:** production recommendations, interview strategies.
   - **Pattern Recognition:** reusable patterns for similar problems.
   - **Mistakes to Avoid:** common pitfalls and bugs.
3. Reference this file in the problem's JavaDoc.
4. Add reference in the `PROGRESS.md` entry.

### When to Create Detailed Learning Files

- Problem required multiple iterations/attempts (3+ solution approaches).
- Deep performance analysis with counter-intuitive results.
- Comparison of 3+ alternative approaches.
- Theory vs practice paradoxes (e.g., O(n) slower than O(n log n)).
- Critical algorithmic insights or patterns.
- User explicitly requests it.

## Do Not

- Create detailed learning files for straightforward problems.
- Duplicate information already in `PROGRESS.md`.
- Create learning files unless specifically requested or complexity warrants it.
