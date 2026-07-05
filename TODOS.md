# DSA Learning TODOs

> **Last Updated:** Sunday, July 5, 2026

---

## 📋 Quick Stats

- **Total TODOs:** 4
- **Pending:** 1
- **Ready to Approach:** 2
- **Completed:** 1

---

## 📑 TODO Categories

### 🔄 Pending (Not Ready Yet)

These TODOs require learning specific concepts/patterns before you can attempt them.

| # | Problem | Approach to Learn | Status | Prerequisites | Date Added |
|---|---------|------------------|--------|---------------|------------|
| 2 | [K Closest Points to Origin (#973)](https://leetcode.com/problems/k-closest-points-to-origin/) | Quickselect | 🔄 Pending | Need to learn: Quickselect algorithm | 2026-03-02 |

---

### ✅ Ready to Approach

These TODOs are ready - you've learned the required patterns from other problems!

| # | Problem | Approach to Apply | Status | Learned From | Date Ready |
|---|---------|-------------------|--------|--------------|------------|
| 1 | [K Closest Points to Origin (#973)](https://leetcode.com/problems/k-closest-points-to-origin/) | Max Heap | ✅ Ready | Top K Frequent Elements (#347) | 2026-02-15 |
| 3 | [K Closest Points to Origin (#973)](https://leetcode.com/problems/k-closest-points-to-origin/) | Min Heap Extract | ✅ Ready | Top K Frequent Elements (#347) | 2026-02-15 |

---

### 🎉 Completed

| # | Problem | Approach | Completed Date | Notes |
|---|---------|----------|----------------|-------|
| 4 | [Top K Frequent Elements (#347)](https://leetcode.com/problems/top-k-frequent-elements/) | Priority Queue (Max Heap) | 2026-02-15 | Implemented as the primary solution. Heap built from all unique elements (O(n + u log u), worst case O(n log n)) rather than bounded to size k, so it doesn't meet the problem's "better than O(n log n)" follow-up — bounding the heap to k or bucket sort would. |

---

## 📚 Pattern → Problems Mapping

This section tracks which patterns you've learned and which TODOs they unlock.

### Heap / Priority Queue
- **Status:** ✅ Learned
- **Unlocked TODOs:** #1, #3 (Ready), #4 (Completed)
- **Learned from:** Top K Frequent Elements (#347)

### Quickselect Algorithm
- **Status:** ❌ Not learned yet
- **Unlocks TODOs:** #2
- **Learn from:** Kth Largest Element in Array (#215), problems with "find kth smallest/largest"

---

## 🎯 Next Steps

1. Implement TODOs #1 (Max Heap) and #3 (Min Heap Extract) for K Closest Points to Origin (#973) — Heap/Priority Queue is now learned
2. When you solve a problem using **Quickselect**, TODO #2 will become ready
3. Check `/todos ready` command to see which TODOs you can now approach

---

## 📝 How to Use This File

- **Adding TODOs:** Run `/todos add` command when you encounter alternative approaches during problem-solving
- **Checking Status:** Run `/todos` or `/todos status` to see current state
- **Finding Ready TODOs:** Run `/todos ready` to see which TODOs you can attempt now
- **Marking Complete:** After implementing an alternative approach, run `/todos complete <id>`
- **Auto-Detection:** When you solve problems, the system automatically marks related TODOs as "Ready"

---

*This file is automatically maintained by the TODO management system.*
