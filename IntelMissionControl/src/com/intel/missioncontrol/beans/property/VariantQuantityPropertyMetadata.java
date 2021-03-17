/**
 * Copyright (c) 2020 Intel Corporation
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.intel.missioncontrol.beans.property;

import com.intel.missioncontrol.common.Optional;
import com.intel.missioncontrol.concurrent.ListenableExecutor;
import com.intel.missioncontrol.concurrent.SynchronizationContext;
import com.intel.missioncontrol.measure.UnitInfo;
import com.intel.missioncontrol.measure.VariantQuantity;

public class VariantQuantityPropertyMetadata extends PropertyMetadata<VariantQuantity> {

    public static class Builder {
        private Optional<String> name = Optional.empty();
        private Optional<Boolean> customBean = Optional.empty();
        private Optional<VariantQuantity> initialValue = Optional.empty();
        private Optional<SynchronizationContext> synchronizationContext = Optional.empty();
        private Optional<IQuantityStyleProvider> quantityStyleProvider = Optional.empty();
        private Optional<UnitInfo<?>[]> unitInfo = Optional.empty();

        /**
         * The name of the property. If this value is not specified, the name of the property field will be
         * automatically detected at runtime. Generally, you should not manually specify the name.
         */
        public Builder name(String name) {
            this.name = Optional.of(name);
            return this;
        }

        /**
         * The initial value of the property. This is also the value which is assumed by the property after calling
         * {@link AsyncProperty#reset()}.
         */
        public Builder initialValue(VariantQuantity value) {
            this.initialValue = Optional.of(value);
            return this;
        }

        /**
         * Specifies the synchronization context that is used when the property is bound to another property, or when
         * any of the -Async methods are called.
         */
        public Builder synchronizationContext(SynchronizationContext synchronizationContext) {
            this.synchronizationContext = Optional.of(synchronizationContext);
            return this;
        }

        /**
         * For regular async properties, the bean value must be a reference to the object instance that contains the
         * property field. Setting this option allows the bean value to be any object reference. If this option is set,
         * automatic name detection will be disabled.
         */
        public Builder customBean(boolean value) {
            this.customBean = Optional.of(value);
            return this;
        }

        public Builder quantityStyleProvider(IQuantityStyleProvider quantityStyleProvider) {
            this.quantityStyleProvider = Optional.of(quantityStyleProvider);
            return this;
        }

        public Builder unitInfo(UnitInfo<?>... unitInfo) {
            this.unitInfo = Optional.of(unitInfo);
            return this;
        }

        public VariantQuantityPropertyMetadata create() {
            return new VariantQuantityPropertyMetadata(
                name,
                customBean,
                initialValue,
                synchronizationContext.isPresent() ? Optional.of(synchronizationContext.get()) : Optional.empty(),
                synchronizationContext.isPresent() ? synchronizationContext.get()::hasAccess : null,
                quantityStyleProvider,
                unitInfo);
        }
    }

    final Optional<IQuantityStyleProvider> quantityStyleProvider;
    final Optional<UnitInfo<?>[]> unitInfo;

    VariantQuantityPropertyMetadata(
            Optional<String> name,
            Optional<Boolean> customBean,
            Optional<VariantQuantity> initialValue,
            Optional<ListenableExecutor> executor,
            HasAccessDelegate hasAccess,
            Optional<IQuantityStyleProvider> quantityStyleProvider,
            Optional<UnitInfo<?>[]> unitInfo) {
        super(name, customBean, initialValue, executor, hasAccess);
        this.quantityStyleProvider = quantityStyleProvider;
        this.unitInfo = unitInfo;
    }

    public IQuantityStyleProvider getQuantityStyleProvider() {
        return quantityStyleProvider.orElse(null);
    }

    public UnitInfo<?>[] getUnitInfo() {
        return unitInfo.orElse(null);
    }

    @Override
    PropertyMetadata<VariantQuantity> merge(PropertyMetadata<VariantQuantity> metadata) {
        PropertyMetadata<VariantQuantity> baseMetadata = super.merge(metadata);

        if (metadata instanceof VariantQuantityPropertyMetadata) {
            VariantQuantityPropertyMetadata quantityMetadata = (VariantQuantityPropertyMetadata)metadata;

            return new VariantQuantityPropertyMetadata(
                baseMetadata.name,
                baseMetadata.customBean,
                baseMetadata.initialValue,
                baseMetadata.executor,
                baseMetadata.hasAccess,
                quantityMetadata.quantityStyleProvider.isPresent()
                    ? quantityMetadata.quantityStyleProvider
                    : quantityStyleProvider,
                quantityMetadata.unitInfo.isPresent() ? quantityMetadata.unitInfo : unitInfo);
        }

        return new VariantQuantityPropertyMetadata(
            baseMetadata.name,
            baseMetadata.customBean,
            baseMetadata.initialValue,
            baseMetadata.executor,
            baseMetadata.hasAccess,
            quantityStyleProvider,
            unitInfo);
    }

}