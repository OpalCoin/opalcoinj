/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        maxTarget = Utils.decodeCompactBits(0x1e0ffff0L);
        resetTarget = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 189;
        addressHeader = 61;
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 45444;
        packetMagic = 0xfbc0b6dbL;
        lastPoWBlock = 260799;
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1390280400L);
        genesisBlock.setNonce(222583475);
        id = ID_MAINNET;
        spendableCoinbaseDepth = 30;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("b868e0d95a3c3c0e0dadc67ee587aaf9dc8acbf99e3b4b3110fad4eb74c1decc"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(10, new Sha256Hash("a198c38a77555a9fbff0b147bf7ce0660416d6abdaa86adaa3a9be97092592ed"));
        checkpoints.put(4000, new Sha256Hash("ad880a4c23d511f04311e98ee77f5163e54cd92f80433e9f3bd6bc2261d50592"));
        checkpoints.put(10000, new Sha256Hash("35d5f9cbd94c15771d5ebebf55ea4bfc649c473c0a868fe4d1832f5b45bd5d0c"));
        checkpoints.put(45189, new Sha256Hash("d10b5da162b922d3cf09c44ea3d533a203c9ab1c442015d7e72f21062d20aeb4"));
        checkpoints.put(244999, new Sha256Hash("0b7bb56edfae2f2f1e71ac39daab16614fccf1a1e02c58d4169521d76d880b42"));

        dnsSeeds = new String[] {
                "seed.reddcoin.com",
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
