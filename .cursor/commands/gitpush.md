---
description: Commit changes in logical groups and push to remote
---

Follow **Rule 7 (GitPush)** from `.cursor/rules/dsa-workflow.mdc`.

Create atomic, logical commits grouped by purpose:
1. **Problem Implementation** - Solution files, learning files, progress tracking
2. **Workflow/Infrastructure** - Rules, commands, configuration
3. **Documentation** - Standalone doc updates
4. **Refactoring/Cleanup** - Other logical changes

Each commit should be atomic, reviewable, and revertable independently.
