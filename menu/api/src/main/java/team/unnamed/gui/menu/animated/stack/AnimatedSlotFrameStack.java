package team.unnamed.gui.menu.animated.stack;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;

import java.util.ArrayList;
import java.util.List;

public interface AnimatedSlotFrameStack {

    AnimatedSlotFrame next();

    AnimatedSlotFrame current();

    int getSize();

    List<Integer> getInvolvedSlots();

    boolean hasNext();

    static AnimatedSlotFrameStackBuilder builder(List<Integer> involvedSlots) {
        return new AnimatedSlotFrameStackBuilder(involvedSlots);
    }

    static AnimatedSlotFrameStackBuilder builder(int... involvedSlots) {
        List<Integer> slots = new ArrayList<>();
        for (int involvedSlot : involvedSlots) {
            slots.add(involvedSlot);
        }

        return builder(slots);
    }

    static AnimatedSlotFrameStack singleFrameStack(AnimatedSlotFrame frame, List<Integer> involvedSlots) {
        return new SingleAnimatedSlotFrameStack(involvedSlots, frame);
    }

    static AnimatedSlotFrameStack singleFrameStack(AnimatedSlotFrame frame, int... involvedSlots) {
        List<Integer> slots = new ArrayList<>();
        for (int involvedSlot : involvedSlots) {
            slots.add(involvedSlot);
        }

        return singleFrameStack(frame, slots);
    }

}
