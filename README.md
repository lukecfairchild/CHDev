CHWorldGuard
=====

## Events
### WorldGuard Events

#### region_change
Fires when a player moves to a block with a different region set than they are currently in.
* `player`
* `from` locationArray
* `to` locationArray
* `fromRegions` An array of regions at the block they are coming from.
* `toRegions` An array of regions at the block they are moving to.