package mytown.api.container;

import mytown.entities.Town;
import mytown.entities.flag.Flag;
import mytown.entities.flag.FlagType;
import myessentials.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class FlagsContainer extends ArrayList<Flag> {

    public boolean contains(FlagType<?> flagType) {
        for (Flag flag : this) {
            if (flag.flagType == flagType) {
                return true;
            }
        }
        return false;
    }

    public <T> Flag<T> get(FlagType<T> flagType) {
        for (Flag flag : this) {
            if (flag.flagType == flagType) {
                return flag;
            }
        }
        return null;
    }

    public void remove(FlagType<?> flagType) {
        for (Iterator<Flag> it = iterator(); it.hasNext(); ) {
            if (it.next().flagType.equals(flagType)) {
                it.remove();
            }
        }
    }

    public <T> T getValue(FlagType<T> flagType) {
        for (Flag flag : this) {
            if (flag.flagType == flagType) {
                return (T)flag.value;
            }
        }
        return null;
    }

    public String toStringForTowns() {
        String formattedFlagList = "";

        for (Flag flag : this) {
            if (!formattedFlagList.equals("")) {
                formattedFlagList += "\\n";
            }
            formattedFlagList += flag.toString(ColorUtils.colorConfigurableFlag);
        }

        String unconfigurableFlags = "";
        for(FlagType flagType : FlagType.values()) {
            if(!contains(flagType)) {
                unconfigurableFlags += "\\n" + (new Flag(flagType, flagType.defaultValue)).toString(ColorUtils.colorUnconfigurableFlag);
            }
        }

        formattedFlagList += unconfigurableFlags;

        return formattedFlagList;
    }

    public String toStringForPlot(Town town) {
        String formattedFlagList = "";

        for (Flag flag : this) {
            if (!formattedFlagList.equals("")) {
                formattedFlagList += "\\n";
            }
            formattedFlagList += flag.toString(ColorUtils.colorConfigurableFlag);
        }

        String unconfigurableFlags = "";
        for(FlagType flagType : FlagType.values()) {
            if(!contains(flagType)) {
                Object value = town.flagsContainer.contains(flagType) ? town.flagsContainer.getValue(flagType) : flagType.defaultValue;
                unconfigurableFlags += "\\n" + (new Flag(flagType, value).toString(ColorUtils.colorUnconfigurableFlag));
            }
        }

        formattedFlagList += unconfigurableFlags;
        return formattedFlagList;
    }

    public String toStringForWild() {
        String formattedFlagList = "";

        for (Flag flag : this) {
            if (!formattedFlagList.equals("")) {
                formattedFlagList += "\\n";
            }
            formattedFlagList += flag.toString();
        }

        return formattedFlagList;
    }
}
