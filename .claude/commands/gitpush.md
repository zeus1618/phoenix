---
description: Commit changes in logical groups and push to remote
---

# Commit & Push

Create atomic, logical commits grouped by purpose, then push to remote.

## Do

1. Run `git status` and `git diff --stat` to analyze all changes.
2. Group changes into logical, atomic commits based on their purpose:

### Commit Group 1: Problem Implementation

Stage problem-specific files: solution file(s) (e.g., `arraysAndHashing/TwoSum.java`), learning files (e.g., `arraysAndHashing/learnings/ProblemName-Learning.md`), `PROGRESS.md` (problem-specific sections), `README.md` (only statistics and category progress sections).

Commit message format:
```
Add [Problem Name] (#X) - [Difficulty]

- Implemented [approach] solution with O(time)/O(space) complexity
- [Key learning or notable aspect]
- Updated progress tracking and documentation

Files: [list main files]
```

### Commit Group 2: Workflow/Infrastructure Changes

Stage workflow files: `CLAUDE.md`, `.claude/commands/*.md`, other infrastructure files.

Commit message format:
```
Update workflow: [brief description of changes]

- [Change 1]
- [Change 2]

Files: [list files]
```

### Commit Group 3: Documentation Updates (if separate from problem)

Stage standalone documentation changes: `README.md` (structural changes, workflow updates), other markdown files not related to a specific problem.

Commit message format:
```
Docs: [brief description]

- [Change 1]
- [Change 2]
```

### Commit Group 4: Refactoring/Cleanup (if applicable)

Stage refactoring changes that don't fit above categories. Use a descriptive commit message.

3. For each commit group with changes:
   - Stage files with `git add <specific files>`.
   - Commit with a descriptive message.
   - Verify with `git status`.
4. Push all commits at once: `git push origin main`.
5. Final verification with `git status` to confirm a clean state.

## Commit Grouping Strategy

- **Atomic principle:** each commit should represent one logical change.
- **Reviewability:** changes in a commit should be related and easy to review together.
- **Revertability:** a commit can be reverted without affecting unrelated changes.
- **Clarity:** commit message should clearly explain the "why" not just the "what".

## Example Session

```
# Problem solved: Group Anagrams
Commit 1: "Add Group Anagrams (#49) - Medium
           - Implemented sorted key approach with O(nk log k) complexity
           - Created detailed learning guide covering 5 alternative approaches
           - Updated progress: 4 problems solved, 1st Medium problem"
           Files: GroupAnagrams.java, GroupAnagrams-Learning.md, PROGRESS.md, README.md

Commit 2: "Update workflow: Add detailed learning file generation
           - Extended update rule with optional learning file creation
           - Updated update command with learning file documentation
           - Added criteria for when to create detailed guides"
           Files: CLAUDE.md, update.md
```

## Do Not

- Create one giant commit with unrelated changes.
- Mix problem implementation with workflow changes in the same commit.
- Push without reviewing `git status` between commits.
