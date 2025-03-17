package org.example.service.serviceImpl;

import org.example.entity.Product;
import org.example.entity.PromoCodes;
import org.example.entity.QuantityDiscount;
import org.example.entity.UserTypes;
import org.example.repository.ProductRepository;
import org.example.repository.PromoCodesRepository;
import org.example.repository.QuantityDiscountRepository;
import org.example.repository.UserTypeRepository;
import org.example.request.ProductRequest;
import org.example.response.AppliedDiscount;
import org.example.response.ProductResponse;
import org.example.service.ProductService;
import org.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private PromoCodesRepository promoCodesRepository;

    @Autowired
    private QuantityDiscountRepository quantityDiscountRepository;

    @Override
    public ProductResponse priceCalculation(ProductRequest productRequest) {
        ProductResponse productResponse = new ProductResponse();
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        Optional<Product> product = productRepository.findById(productRequest.getProductId());
        if (product.isPresent()) {
            Double finalPrice = product.get().getBasePrice();
            if (!productRequest.getUserType().isEmpty()) {
                finalPrice = getFinalPriceForUserTypes(productRequest.getUserType(), finalPrice,appliedDiscounts);
            } else if (!productRequest.getPromoCode().isEmpty()) {
                finalPrice = getFinalPriceForPromoCode(productRequest.getPromoCode(),finalPrice,appliedDiscounts);
            }else {
                finalPrice = getFinalPriceForQuantity(productRequest.getQuantity(),finalPrice,appliedDiscounts);
            }
            productRequest.setProductId(product.get().getId());
            productResponse.setOriginalPrice(product.get().getBasePrice());
            productResponse.setFinalPrice(finalPrice);
            productResponse.setAppliedDiscounts(appliedDiscounts);
            productResponse.setTotalSavings(product.get().getBasePrice()-finalPrice);
        }

        return productResponse;
    }

    private Double getFinalPriceForQuantity(Integer quantity, Double finalPrice, List<AppliedDiscount> appliedDiscounts) {
        Double discount = 0.0;
        List<QuantityDiscount> quantityDiscounts = quantityDiscountRepository.findAll();
        List<Integer> quantityList = quantityDiscounts.stream().map(QuantityDiscount::getMinQuantity).toList();
        if(quantityList.contains(quantity)){
            QuantityDiscount quantityDiscount = quantityDiscountRepository.findByMinQuantity(quantity);
            discount = ((quantityDiscount.getDiscountPercentage()/100)* finalPrice);

        }
        return finalPrice - discount;
    }

    private Double getFinalPriceForPromoCode(String promoCode, Double finalPrice, List<AppliedDiscount> appliedDiscounts) {
        Double discount = 0.0;
        AppliedDiscount appliedDiscount = new AppliedDiscount();
        List<PromoCodes> promoCodes = promoCodesRepository.findAll();
        List<String> promoCodesList = promoCodes.stream().map(PromoCodes::getCode).toList();
        if(promoCodesList.contains(promoCode)){
            PromoCodes promoCodeFromDB = promoCodesRepository.findById(promoCode).get();
            discount = ((promoCodeFromDB.getDiscountPercentage()/100)* finalPrice);
            appliedDiscount.setType(CommonUtil.PROMO_CODE);
            appliedDiscount.setPercentage(promoCodeFromDB.getDiscountPercentage());
            appliedDiscounts.add(appliedDiscount);
        }
        return finalPrice - discount;
    }

    private Double getFinalPriceForUserTypes(String userType, Double finalPrice, List<AppliedDiscount> appliedDiscounts) {
        Double discount = 0.0;
        AppliedDiscount appliedDiscount = new AppliedDiscount();
        List<UserTypes> userTypesList = userTypeRepository.findAll();
        List<String> userTypes = userTypesList.stream().map(UserTypes::getType).toList();
        if(userTypes.contains(userType)){
                UserTypes type = userTypeRepository.findById(userType).get();
                discount = ((type.getDiscountPercentage()/100)*finalPrice);
                appliedDiscount.setType(type.getType()+ CommonUtil.USER);
                appliedDiscount.setPercentage(type.getDiscountPercentage());
                appliedDiscounts.add(appliedDiscount);
        }
        return finalPrice - discount;
    }
}
