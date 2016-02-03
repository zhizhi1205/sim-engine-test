package com.thinkive.simtrade.service;

import java.util.List;

import com.thinkive.simtrade.bean.Order;

public class StockMatchmaking {

	
	private StockDealService dealService;
	
	private MatchOrderService matchOrderService;
	
	private TradeOrderService tradeOrderService;
	/**
	 * 查询带撮合的订单
	 * 
	 * @return
	 */
	List<Order> findNeedMatchOrder() {
		return null;
	};

	void prepareMatchmaking() {
		List<Order> matchOrders = tradeOrderService.findNeedMatchOrder();
		if (matchOrders != null && matchOrders.size() > 0) {
			matchOrderService.addOrderQueue(matchOrders);
		}

	}

	void doMatchmaking() {
		// 获取待撮合订单
		Order matchOrder = matchOrderService.getMatchOrder();
		if (matchOrder.isBuy()) {
			dealService.dealBuy(matchOrder);
		} else if (matchOrder.isSell()) {
			dealService.dealSell(matchOrder);
		} else if (matchOrder.isCancel()) {
			dealService.dealCancel(matchOrder);
		}

	}
	
	

	void finallyMatchmaking() {
		List<Order> doneOrders=matchOrderService.findDoneMatchOrder();
		tradeOrderService.updateMatchOrder(doneOrders);
	}


	 

	private Order getMatchOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	private void addOrderQueue(List<Order> matchOrders) {
		// TODO Auto-generated method stub

	}

}
