package com.alphawallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.alphawallet.app.interact.AddTokenInteract;
import com.alphawallet.app.interact.FetchTransactionsInteract;
import com.alphawallet.app.repository.EthereumNetworkRepositoryType;
import com.alphawallet.app.router.ConfirmationRouter;
import com.alphawallet.app.router.MyAddressRouter;
import com.alphawallet.app.service.AssetDefinitionService;
import com.alphawallet.app.service.GasService;
import com.alphawallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class SendViewModelFactory implements ViewModelProvider.Factory {

    private final ConfirmationRouter confirmationRouter;
    private final MyAddressRouter myAddressRouter;
    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AddTokenInteract addTokenInteract;
    private final GasService gasService;
    private final AssetDefinitionService assetDefinitionService;

    public SendViewModelFactory(ConfirmationRouter confirmationRouter,
                                MyAddressRouter myAddressRouter,
                                EthereumNetworkRepositoryType networkRepository,
                                TokensService tokensService,
                                FetchTransactionsInteract fetchTransactionsInteract,
                                AddTokenInteract addTokenInteract,
                                GasService gasService,
                                AssetDefinitionService assetDefinitionService) {
        this.confirmationRouter = confirmationRouter;
        this.myAddressRouter = myAddressRouter;
        this.networkRepository = networkRepository;
        this.tokensService = tokensService;
        this.gasService = gasService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.addTokenInteract = addTokenInteract;
        this.assetDefinitionService = assetDefinitionService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendViewModel(confirmationRouter, myAddressRouter, networkRepository, tokensService,
                fetchTransactionsInteract, addTokenInteract, gasService, assetDefinitionService);
    }
}
