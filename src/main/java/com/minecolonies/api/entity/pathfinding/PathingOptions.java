package com.minecolonies.api.entity.pathfinding;

/**
 * Configuration values for pathing, used by pathjobs and normally set through the navigator
 */
public class PathingOptions
{
    // x2: Weak dislike, x3: clear dislike, x4 strong dislike x5 very strong dislike

    /**
     * Additional cost of jumping
     */
    public double jumpCost = 3D;

    /**
     * Additional cost of dropping
     */
    public double dropCost = 2D;

    /**
     * Cost improvement of paths - base 1.
     */
    public double onPathCost = 1 / 4d;

    /**
     * Cost improvement of paths - base 1.
     */
    public double onRailCost = 1 / 10D;

    /**
     * The rails exit cost.
     */
    public double railsExitCost = 5;

    /**
     * Additional cost of swimming - base 1.
     */
    public double swimCost = 3D;

    /**
     * Additional cost of cave air.
     */
    public double caveAirCost = 4D;

    /**
     * Additional cost enter entering water
     */
    public double swimCostEnter = 25D;

    /**
     * Cost to traverse trap doors
     */
    public double traverseToggleAbleCost = 10D;

    /**
     * Cost to climb a non ladder.
     */
    public double nonLadderClimbableCost = 4D;

    /**
     * Whether to use minecart rail pathing
     */
    private boolean canUseRails  = false;
    /**
     * Can swim
     */
    private boolean canSwim      = false;
    /**
     * Allowed to enter doors?
     */
    private boolean enterDoors   = false;
    /**
     * Allowed to open doors?
     */
    private boolean canOpenDoors = false;
    /**
     * Whether to path through vines.
     */
    private boolean canClimbNonLadders = false;

    /**
     * Whether to path through dangerous blocks.
     */
    private boolean canPassDanger  = false;

    public PathingOptions()
    {}

    public boolean canOpenDoors()
    {
        return canOpenDoors;
    }

    public void setCanOpenDoors(final boolean canOpenDoors)
    {
        this.canOpenDoors = canOpenDoors;
    }

    public boolean canUseRails()
    {
        return canUseRails;
    }

    public boolean canClimbNonLadders()
    {
        return canClimbNonLadders;
    }

    public void setCanUseRails(final boolean canUseRails)
    {
        this.canUseRails = canUseRails;
    }

    public void setCanClimbNonLadders(final boolean canClimbNonLadders)
    {
        this.canClimbNonLadders = canClimbNonLadders;
    }

    public boolean canSwim()
    {
        return canSwim;
    }

    public void setCanSwim(final boolean canSwim)
    {
        this.canSwim = canSwim;
    }

    public boolean canEnterDoors()
    {
        return enterDoors;
    }

    public void setEnterDoors(final boolean enterDoors)
    {
        this.enterDoors = enterDoors;
    }

    public void setPassDanger(final boolean danger)
    {
        this.canPassDanger = danger;
    }

    public boolean canPassDanger()
    {
        return canPassDanger;
    }

    public PathingOptions withStartSwimCost(final double startSwimCost)
    {
        swimCostEnter = startSwimCost;
        return this;
    }

    public PathingOptions withSwimCost(final double swimCost)
    {
        this.swimCost = swimCost;
        return this;
    }

    public PathingOptions withJumpCost(final double jumpCost)
    {
        this.jumpCost = jumpCost;
        return this;
    }

    public PathingOptions withDropCost(final double dropCost)
    {
        this.dropCost = dropCost;
        return this;
    }

    public PathingOptions withOnPathCost(final double onPathCost)
    {
        this.onPathCost = onPathCost;
        return this;
    }

    public PathingOptions withOnRailCost(final double onRailCost)
    {
        this.onRailCost = onRailCost;
        return this;
    }

    public PathingOptions withRailExitCost(final double railExitCost)
    {
        railsExitCost = railExitCost;
        return this;
    }

    public PathingOptions withToggleCost(final double toggleCost)
    {
        traverseToggleAbleCost = toggleCost;
        return this;
    }

    public PathingOptions withNonLadderClimbableCost(final double nonLadderClimbableCost)
    {
        this.nonLadderClimbableCost = nonLadderClimbableCost;
        return this;
    }

    /**
     * Sets swimming ability
     *
     * @param canswim whether swimming is allowed
     * @return
     */
    public PathingOptions withCanSwim(final boolean canswim)
    {
        setCanSwim(canswim);
        return this;
    }

    /**
     * Set door opening capability
     * @param canEnter whether we can enter doors
     * @return
     */
    public PathingOptions withCanEnterDoors(final boolean canEnter)
    {
        setEnterDoors(canEnter);
        return this;
    }

    /**
     * Imports all options from the given other pathing options
     * @param pathingOptions
     */
    public void importFrom(final PathingOptions pathingOptions)
    {
        jumpCost = pathingOptions.jumpCost;
        dropCost = pathingOptions.dropCost;
        onPathCost = pathingOptions.onPathCost;
        onRailCost = pathingOptions.onRailCost;
        railsExitCost = pathingOptions.railsExitCost;
        swimCost = pathingOptions.swimCost;
        caveAirCost = pathingOptions.caveAirCost;
        swimCostEnter = pathingOptions.swimCostEnter;
        traverseToggleAbleCost = pathingOptions.traverseToggleAbleCost;
        nonLadderClimbableCost = pathingOptions.nonLadderClimbableCost;
        canUseRails = pathingOptions.canUseRails;
        canSwim = pathingOptions.canSwim;
        enterDoors = pathingOptions.enterDoors;
        canOpenDoors = pathingOptions.canOpenDoors;
        canClimbNonLadders = pathingOptions.canClimbNonLadders;
        canPassDanger = pathingOptions.canPassDanger;
    }

}
