package gtexpert.api.recipes.ingredients;

import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GTENBTMatchers {

    /**
     * Return true if tag has an entry where the value is equal to the condition's value.
     * If NBTTagCompound is found, evaluates recursively.
     */
    public static final NBTMatcher RECURSIVE_EQUAL_TO = new NBTMatcher() {
        @Override
        public boolean evaluate(@Nullable NBTTagCompound tag, @Nullable NBTCondition condition)  {
            if (condition == null || condition.tagType == null) {
                return false;
            }
            if (NBTMatcher.hasKey(tag, condition.nbtKey, condition.tagType.typeId)) {
                if (NBTTagType.isNumeric(condition.tagType)) {
                    return Objects.equals(tag.getLong(condition.nbtKey), ((Number) condition.value).longValue());
                }
                switch (condition.tagType) {
                    case BYTE_ARRAY:
                        return tag.getByteArray(condition.nbtKey).equals(condition.value);
                    case STRING:
                        return tag.getString(condition.nbtKey).equals(condition.value);
                    case LIST:
                    case LONG_ARRAY:
                        return false; // skip as we don't have AT
                    case COMPOUND:
                        if (condition.value instanceof NBTCondition) {
                            return evaluate(tag.getCompoundTag(condition.nbtKey), (NBTCondition) condition.value);
                        } else {
                            return tag.getCompoundTag(condition.nbtKey).equals(condition.value);
                        }
                    case INT_ARRAY:
                        return tag.getIntArray(condition.nbtKey).equals(condition.value);
                }
            }
            return false;
        }
    };
}
