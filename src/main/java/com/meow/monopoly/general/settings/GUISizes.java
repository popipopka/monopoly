package com.meow.monopoly.general.settings;

import com.meow.monopoly.gui.GUIUtil;

public enum GUISizes {
    GAME_BOARD((int) (GUIUtil.getScreenWidth() * 0.55)),
    GAME_BOARD_PART(GAME_BOARD.get() / 13),

    LEFT_RIGHT_WIDTH(GAME_BOARD_PART.get() * 2),
    LEFT_RIGHT_HEIGHT(GAME_BOARD.get() / 8),

    TOP_BOTTOM_WIDTH(GAME_BOARD_PART.get()),
    TOP_BOTTOM_HEIGHT(GAME_BOARD_PART.get() * 2),

    PLAYER_ICON((int) (GAME_BOARD_PART.get() * 0.35)),

    FACE_DICE(GAME_BOARD_PART.get()),
    FACE_DICE_CIRCLE((int) (GAME_BOARD_PART.get() * 0.12)),

    INFO_BOARD_WIDTH((int) (GUIUtil.getScreenWidth() * 0.25)),
    CONTROL_BOARD_WIDTH((int) (GUIUtil.getScreenWidth() * 0.2)),

    CONTROL_ELEM_WIDTH((int) (INFO_BOARD_WIDTH.get() * 0.35)),
    CONTROL_ELEM_HEIGHT((int) (GAME_BOARD.get() * 0.05)),

    LIST_WIDTH((int) (INFO_BOARD_WIDTH.get() * 0.9)),
    LIST_HEIGHT((int) (GAME_BOARD.get() * 0.4)),

    LIST_ITEM_WIDTH(LIST_WIDTH.get()),
    LIST_ITEM_HEIGHT((int) (GAME_BOARD.get() * 0.05)),

    OBJ_LIST_ITEM_RECT_SIZE(LIST_ITEM_HEIGHT.get()),

    PLAYER_LIST_ITEM_WIDTH((int) (INFO_BOARD_WIDTH.get() * 0.48)),
    PLAYER_LIST_ITEM_HEIGHT((int) (PLAYER_LIST_ITEM_WIDTH.get() * 0.42)),

    PLAYER_LIST_ITEM_ICON_SIZE((int) (INFO_BOARD_WIDTH.get() * 0.14)),

    CELL_AVATAR_RECT_SIZE((int) (INFO_BOARD_WIDTH.get() * 0.32));

    private final int size;

    GUISizes(int size) {
        this.size = size;
    }

    public int get() {
        return size;
    }
}
