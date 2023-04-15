package com.collidacube.prison.prisoncore.data.economy.vault;

import com.collidacube.prison.prisoncore.data.PrisonEconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse;

public class VaultAdapter {

    public static EconomyResponse adaptEconomyResponse(PrisonEconomyResponse response) {
        return new EconomyResponse(response.amount(), response.balance(), adaptResponseType(response.type()), response.errorMessage());
    }

    public static EconomyResponse.ResponseType adaptResponseType(PrisonEconomyResponse.ResponseType responseType) {
        if (responseType == PrisonEconomyResponse.ResponseType.SUCCESS) return EconomyResponse.ResponseType.SUCCESS;
        if (responseType == PrisonEconomyResponse.ResponseType.FAILURE) return EconomyResponse.ResponseType.FAILURE;
        if (responseType == PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED) return EconomyResponse.ResponseType.NOT_IMPLEMENTED;
        return null;
    }

}
